package org.aknife.connection.handler;

import io.netty.channel.Channel;
import io.netty.channel.SimpleChannelInboundHandler;
import org.aknife.connection.initializer.SystemInitializer;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.connection.link.user.UserChannelConnection;
import org.aknife.message.model.Message;
import org.aknife.business.user.model.User;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AbstractServerHandler
 * @Author HeZiLong
 * @Data 2021/1/13 15:56
 */
abstract class AbstractServerHandler extends SimpleChannelInboundHandler<Message> {




    ConcurrentHashMap<Channel, User> userChannel = null;

    ApplicationContext ioc = null;

    public AbstractServerHandler(){
        userChannel = UserChannelConnection.getAllUserChannel();
        this.ioc = SystemInitializer.getIoc();
    }

    /**
     * 返回指定协议对象对应的编号
     * @param clazz
     * @return
     */
    public int getClassCode(Class clazz){
        return PacketFixedConsts.getCodeByClass(clazz);
    }
}
