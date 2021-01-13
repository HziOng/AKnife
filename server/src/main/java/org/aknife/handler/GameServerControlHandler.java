package org.aknife.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.beans.binding.ObjectExpression;
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
public class GameServerControlHandler extends AbstractServerHandler {

    public GameServerControlHandler(){    }

    public GameServerControlHandler(ConcurrentHashMap protocolMap,ConcurrentHashMap classMap, ApplicationContext context){
        super(protocolMap, classMap, context);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        log.info("运行至服务端控制处理程序ServerControlHandler中，得到了"+message);
        Channel channel = channelHandlerContext.channel();
        try {
            User nowUser = userChannel.get(channel);
            // 协议分发功能
            Method operaMethod = protocolMap.get(message.getType());
            Object operaObject = ioc.getBean(operaMethod.getDeclaringClass());
            int status = (int) operaMethod.invoke(operaObject, nowUser, message.getData());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
