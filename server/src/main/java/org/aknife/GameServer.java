package org.aknife;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.aknife.connection.handler.GameServerInitializer;
import org.aknife.connection.initializer.SystemInitializer;
import org.aknife.message.transmitter.PacketTransmitterUtil;

import java.net.InetSocketAddress;

/**
 * 游戏服务器
 * @ClassName GameServer
 * @Author HeZiLong
 * @Data 2021/1/11 10:15
 */
public class GameServer {

    private int port;

    public GameServer(){}

    public GameServer(int port){
        this.port = port;
    }

    public void run(){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            SystemInitializer systemInitializer = new SystemInitializer();
            PacketTransmitterUtil.initTransmitter(systemInitializer);
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,work)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new GameServerInitializer(systemInitializer));

            ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
            System.out.println(" server start up on port : " + port);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
