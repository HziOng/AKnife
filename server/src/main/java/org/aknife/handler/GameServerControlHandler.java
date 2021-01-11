package org.aknife.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;
import org.aknife.message.model.Message;
import org.aknife.user.packet.CM_UserLogin;
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

    private ApplicationContext ioc = null;

    public GameServerControlHandler(){super();}

    public GameServerControlHandler(ConcurrentHashMap protocolMap, ApplicationContext context){
        this.protocolMap = protocolMap;
        this.ioc = context;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        log.info("运行至服务端控制处理程序ServerControlHandler中，得到了"+o);

        try {
            Message message = (Message) o;
            Method operaMethod = protocolMap.get(message.getType());
            Object operaObject = ioc.getBean(operaMethod.getDeclaringClass());
            Class[] paramClass = operaMethod.getParameterTypes();
            if(paramClass.length == 0){
                log.info("运行方法无参数！");
            }
            operaMethod.invoke(operaObject, JSON.parseObject(message.getData().toString(),paramClass[0]));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
