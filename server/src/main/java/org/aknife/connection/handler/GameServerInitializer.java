package org.aknife.connection.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.java.Log;
import org.aknife.connection.initializer.SystemInitializer;
import org.aknife.message.codec.GameMessageDecoder;
import org.aknife.message.codec.GameMessageEncoder;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 通道初始化器
 * @ClassName GameServerInitializer
 * @Author HeZiLong
 * @Data 2021/1/11 10:24
 */
@Log
public class GameServerInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 存储协议类型和调用的service方法的映射关系
     */
    private SystemInitializer initializer;

    /**
     * 存放协议编码和协议对象的映射关系
     */
    private ConcurrentHashMap<Integer, Class> classMap = new ConcurrentHashMap();

    /**
     * Spring中的IOC容器
     */
    private ApplicationContext ioc = null;


    public GameServerInitializer() {
        this.ioc = SystemInitializer.getIoc();
    }


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
        pipeline.addLast("decoder", new GameMessageDecoder());
        pipeline.addLast("encoder", new GameMessageEncoder());
        pipeline.addLast("accessHandler", new AccessControlHandler());
        pipeline.addLast("heartBeatHandler", new HeartBeatServerHandler());
        pipeline.addLast("controlHandler", new GameServerControlHandler());

    }

}
