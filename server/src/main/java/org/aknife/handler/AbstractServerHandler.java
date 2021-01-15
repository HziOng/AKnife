package org.aknife.handler;

import io.netty.channel.Channel;
import io.netty.channel.SimpleChannelInboundHandler;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.link.user.UserChannelConnection;
import org.aknife.message.model.Message;
import org.aknife.business.user.model.User;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AbstractServerHandler
 * @Author HeZiLong
 * @Data 2021/1/13 15:56
 */
abstract class AbstractServerHandler extends SimpleChannelInboundHandler<Message> {

    /**
     * 存储协议类型和调用的service方法的映射关系
     */
    ConcurrentHashMap<Integer, Method> protocolMap = null;


    ConcurrentHashMap<Channel, User> userChannel = null;

    ApplicationContext ioc = null;

    public AbstractServerHandler(){
        userChannel = UserChannelConnection.getAllUserChannel();
    }

    public AbstractServerHandler(ConcurrentHashMap protocolMap,ConcurrentHashMap classMap, ApplicationContext context){
        this();
        this.protocolMap = protocolMap;
        this.ioc = context;
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
