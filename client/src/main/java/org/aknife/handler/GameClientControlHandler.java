package org.aknife.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;
import org.aknife.business.user.model.User;
import org.aknife.message.model.Message;

import java.lang.reflect.Method;

/**
 * @ClassName GameClientControlHandler
 * @Author HeZiLong
 * @Data 2021/1/11 10:58
 */
@Log
public class GameClientControlHandler extends AbstractClientHandler {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        log.info("运行至客户端控制处理程序ClientControlHandler中，得到了"+message);
        Channel channel = channelHandlerContext.channel();
        try {
            new ThreadLocal<>().get();
            // 协议分发功能
            Method operaMethod = protocolMap.get(message.getType());
            Object operaObject = ioc.getBean(operaMethod.getDeclaringClass());
            int status = (int) operaMethod.invoke(operaObject, message.getData());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
