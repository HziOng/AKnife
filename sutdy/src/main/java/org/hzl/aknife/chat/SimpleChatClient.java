package org.hzl.aknife.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import jdk.internal.util.xml.impl.Input;
import org.hzl.aknife.chat.initializer.SimpleChatClientInitializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;

/**
 * 客户端启动器
 * @ClassName SimpleChatClient
 * @Author HeZiLong
 * @Data 2021/1/6 12:29
 */
public class SimpleChatClient {

    private String host;
    private int port;

    public SimpleChatClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SimpleChatClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                channel.writeAndFlush(input.readLine() + "\r\n");
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new SimpleChatClient("127.0.0.1", 8080).run();
    }
}
