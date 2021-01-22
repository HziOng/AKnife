package org.aknife.connection.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.SneakyThrows;
import lombok.extern.java.Log;
import org.aknife.connection.initializer.SystemInitializer;
import org.aknife.connection.thread.PersonalThreadDistributionUtil;
import org.aknife.message.model.Message;
import org.aknife.business.user.model.User;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务端控制不同消息任务执行不同任务的Handler,协议分发器
 * @ClassName GameServerControlHandler
 * @Author HeZiLong
 * @Data 2021/1/11 10:22
 */
@Log
public class GameServerControlHandler extends AbstractServerHandler {

    /**
     * 存储协议类型和调用的service方法的映射关系
     */
    // 存储协议类型和调用的service方法的映射关系
    /**
     * 协议中独自运行的方法
     */
    private ConcurrentHashMap<Integer, Method> protocolUniqueMap = null;

    public GameServerControlHandler(){
        this.protocolUniqueMap = SystemInitializer.getProtocolMap();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        log.info("运行至服务端控制处理程序ServerControlHandler中，得到了"+message);
        Channel channel = channelHandlerContext.channel();

        try {
            User nowUser = userChannel.get(channel);
            // 首先将任务交给用户个人线程执行
            PersonalThreadDistributionUtil.runTask(nowUser.getUserID(),new Runnable(){
                @SneakyThrows
                @Override
                public void run() {
                    // 协议分发功能
                    Method operaMethod = protocolUniqueMap.get(message.getType());
                    Object operaObject = ioc.getBean(operaMethod.getDeclaringClass());
                    operaMethod.invoke(operaObject, nowUser, message.getData());
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
