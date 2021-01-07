package org.hzl.aknife.hzlpNetty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.hzl.aknife.hzlpNetty.server.handler.HzlpServerInitializer;

import java.net.InetSocketAddress;

/**
 * 简易的Http连接器服务端
 * @ClassName HttpServer
 * @Author HeZiLong
 * @Data 2021/1/7 16:40
 */
public class HzlpServer {

    int port;

    public HzlpServer(int port){
        this.port = port;
    }

    /**
     * 方法Http连接器服务端运行主体
     */
    public void run() throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,work)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HzlpServerInitializer());

            ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
            System.out.println(" server start up on port : " + port);
            f.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new HzlpServer(8080).run();
    }
}
