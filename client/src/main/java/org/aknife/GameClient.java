package org.aknife;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.aknife.connection.handler.AbstractClientHandler;
import org.aknife.connection.handler.GameClientInitializer;
import org.aknife.message.transmitter.PacketTransmitter;

/**
 * 游戏Java版客户端
 * @ClassName GameClient
 * @Author HeZiLong
 * @Data 2021/1/11 10:49
 */
public class GameClient {

    private String host;

    private int port;

    public GameClient(){}

    public GameClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    /**
     *
     */
    public void run(){
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            SystemInitializer initializer = new SystemInitializer();
            AbstractClientHandler.initHandler(initializer);
            Bootstrap bootstrap = new Bootstrap()
                    .group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new GameClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();
            PacketTransmitter.initTransmitter(channel);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            work.shutdownGracefully();
        }
    }
}
