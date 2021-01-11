package org.aknife.discard;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.aknife.discard.handler.DiscardClientHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DiscardClient {

    private int port ;
    private String host ;
    public static final int SIZE = 128;

    public DiscardClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .option(ChannelOption.TCP_NODELAY, true)
             .handler(new ChannelInitializer<SocketChannel>() {
                  @Override
                  public void initChannel(SocketChannel ch) throws Exception {
                      ChannelPipeline p = ch.pipeline();
                      p.addLast(new DiscardClientHandler());
                  }
             });
            Channel channel = b.connect(host, port).sync().channel();
            while(true){
                String msg = input.readLine();
                channel.writeAndFlush(msg+"\r\n");
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 8080;

        if(args.length >= 1){
            host = args[0];
        }
        if(args.length == 2){
            port = Integer.parseInt(args[1]);
        }
        new DiscardClient(host, port).run();
    }
}
