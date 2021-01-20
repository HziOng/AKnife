package org.aknife.connection.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.java.Log;
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
            // 协议分发功能
            Method operaMethod = protocolMap.get(message.getType());
            Object operaObject = ioc.getBean(operaMethod.getDeclaringClass());
            operaMethod.invoke(operaObject, message.getData());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
