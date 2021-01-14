package org.aknife;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.aknife.handler.GameClientInitializer;
import org.aknife.business.user.swing.SwingLoginForm;

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
            Bootstrap bootstrap = new Bootstrap()
                    .group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new GameClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();
            new SwingLoginForm(channel);
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            work.shutdownGracefully();
        }
    }
}
