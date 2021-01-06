package org.hzl.aknife.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.hzl.aknife.chat.initializer.SimpleChatServerInitializer;

/**
 * 服务端启动器
 * @ClassName SimpleChatClient
 * @Author HeZiLong
 * @Data 2021/1/6 12:29
 */
public class SimpleChatServer {

    private int port;

    public SimpleChatServer(int port){
        this.port = port;
    }

    /**
     * 服务端方法运行主体
     * @throws Exception
     */
    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SimpleChatServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            System.out.println("SimpleChatServer 启动");
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            System.out.println("SimpleChatSever 关闭");
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if(args.length >= 1){
            port = Integer.parseInt(args[0]);
        }else {
            port = 8080;
        }
        new SimpleChatServer(port).run();
    }
}
