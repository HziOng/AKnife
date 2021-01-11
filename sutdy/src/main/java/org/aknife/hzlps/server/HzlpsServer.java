package org.aknife.hzlps.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.aknife.hzlps.server.handler.HzlpsServerInitializer;

import java.net.InetSocketAddress;

/**
 * @ClassName HzlpsServer
 * @Author HeZiLong
 * @Data 2021/1/8 14:43
 */
public class HzlpsServer {

    int port;

    public HzlpsServer(int port){
        this.port = port;
    }


    /**
     *
     * @throws Exception
     */
    public void run() throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,work)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HzlpsServerInitializer());

            ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
            System.out.println(" server start up on port : " + port);
            f.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new HzlpsServer(8081).run();
    }
}
