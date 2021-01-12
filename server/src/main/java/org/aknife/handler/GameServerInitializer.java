package org.aknife.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.java.Log;
import org.aknife.SystemInitializer;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.codec.GameMessageDecoder;
import org.aknife.message.codec.GameMessageEncoder;
import org.aknife.util.annotation.Operating;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.io.IOException;
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

    private ConcurrentHashMap<Integer, Method> classMap = new ConcurrentHashMap();

    /**
     * Spring中的IOC容器
     */
    private ApplicationContext ioc = null;

    public GameServerInitializer() {
        super();
        protocolMap = new ConcurrentHashMap<>();
        classMap = new ConcurrentHashMap<>();
    }

    public GameServerInitializer(SystemInitializer systemInitializer) {
        this.protocolMap = systemInitializer.getProtocolMap();
        this.classMap = systemInitializer.getClassMap();
        this.ioc = systemInitializer.getIoc();
    }


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("decoder", new GameMessageDecoder());
        pipeline.addLast("encoder", new GameMessageEncoder());
        pipeline.addLast("controlHandler", new GameServerControlHandler(protocolMap, ioc));

    }

}
