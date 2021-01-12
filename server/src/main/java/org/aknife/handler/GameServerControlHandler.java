package org.aknife.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;
import org.aknife.link.user.UserChannelConnection;
import org.aknife.message.model.Message;
import org.aknife.user.model.User;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务端控制不同消息任务执行不同任务的Handler,协议分发器
 * @ClassName GameServerControlHandler
 * @Author HeZiLong
 * @Data 2021/1/11 10:22
 */
@Log
public class GameServerControlHandler extends SimpleChannelInboundHandler {

    /**
     * 存储协议类型和调用的service方法的映射关系
     */
    private ConcurrentHashMap<Integer, Method> protocolMap = null;

    private ConcurrentHashMap<Channel, User> userChannel = null;

    private ApplicationContext ioc = null;

    public GameServerControlHandler(){
        super();
        userChannel = UserChannelConnection.getAllUserChannel();
    }

    public GameServerControlHandler(ConcurrentHashMap protocolMap, ApplicationContext context){
        this();
        this.protocolMap = protocolMap;
        this.ioc = context;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        log.info("运行至服务端控制处理程序ServerControlHandler中，得到了"+o);
        Channel channel = channelHandlerContext.channel();
        try {
            // user-channel映射处理
            if(userChannel.get(channel) == null){
                User user = new User("未登录用户", "未确定密码");
                userChannel.put(channel, user);
            }

            // 协议分发功能
            Message message = (Message) o;
            Method operaMethod = protocolMap.get(message.getType());
            System.out.println(operaMethod);
            Object operaObject = ioc.getBean(operaMethod.getDeclaringClass());
            Class[] paramClass = operaMethod.getParameterTypes();
            System.out.println(userChannel.get(channelHandlerContext.channel()));
            // 执行协议对应的处理方法
            int status =
                    (int) operaMethod.invoke(operaObject,
                    userChannel.get(channelHandlerContext.channel()),
                    JSON.parseObject(message.getData().toString(),paramClass[1]));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
