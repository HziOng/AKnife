package org.aknife.connection.handler;

import io.netty.channel.SimpleChannelInboundHandler;
import org.aknife.SystemInitializer;
import org.aknife.business.user.model.User;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.model.Message;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AbstractClientHandler
 * @Author HeZiLong
 * @Data 2021/1/15 15:18
 */
public abstract class AbstractClientHandler extends SimpleChannelInboundHandler<Message> {

    protected static User user;

    /**
     * 存储协议类型和调用的service方法的映射关系
     */
    static ConcurrentHashMap<Integer, Method> protocolMap = null;

    static ApplicationContext ioc = null;

    public int getCode(Class clazz){
        return PacketFixedConsts.getCodeByClass(clazz);
    }

    /**
     * 初始换Handler共同数据
     */
    public static void initHandler(SystemInitializer initializer){
        protocolMap = initializer.getProtocolMap();
        ioc = initializer.getIoc();
    }

}
