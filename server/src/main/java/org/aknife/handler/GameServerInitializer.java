package org.aknife.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.java.Log;
import org.aknife.SystemInitializer;
import org.aknife.message.codec.GameMessageDecoder;
import org.aknife.message.codec.GameMessageEncoder;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName GameServerInitializer
 * @Author HeZiLong
 * @Data 2021/1/11 10:24
 */
@Log
public class GameServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 存储协议类型和调用的service方法的映射关系
     */
    private ConcurrentHashMap<Integer, Method> protocolMap = new ConcurrentHashMap();

    /**
     * 存放协议编码和协议对象的映射关系
     */
    private ConcurrentHashMap<Integer, Class> classMap = new ConcurrentHashMap();

    /**
     * Spring中的IOC容器
     */
    private ApplicationContext ioc = null;

    public GameServerInitializer() {
        protocolMap = new ConcurrentHashMap<>();
        classMap = new ConcurrentHashMap<>();
    }

    public GameServerInitializer(SystemInitializer systemInitializer) {
        this.protocolMap = systemInitializer.getProtocolMap();
        this.ioc = systemInitializer.getIoc();
    }


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("decoder", new GameMessageDecoder());
        pipeline.addLast("encoder", new GameMessageEncoder());
        pipeline.addLast("heartBeatHandler", new HeartBeatServerHandler(classMap, ioc));
        pipeline.addLast("controlHandler", new GameServerControlHandler(protocolMap, classMap, ioc));

    }

}
