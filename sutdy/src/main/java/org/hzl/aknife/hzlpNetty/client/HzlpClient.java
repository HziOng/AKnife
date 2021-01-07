package org.hzl.aknife.hzlpNetty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.hzl.aknife.hzlpNetty.api.util.ConsantUtil;
import org.hzl.aknife.hzlpNetty.client.handler.HzlpClientInitializer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 简易的Http连接器客户端
 * @ClassName HttpClient
 * @Author HeZiLong
 * @Data 2021/1/7 16:40
 */
public class HzlpClient {

    private String host;
    private int port;

    public HzlpClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    /**
     * Http连接器客户端方法运行主主体
     */
    public void run() throws Exception{
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new HzlpClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();

            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(ConsantUtil.PACKAGE_HEADER);
            buf.writeInt(ConsantUtil.MODEL_ONE);
            buf.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

            JSONObject msg = new JSONObject();
            msg.put("name","贺子龙");
            msg.put("age","20");
            msg.put("id","00001");
            buf.writeBytes(msg.toString().getBytes());

            channel.writeAndFlush(buf);

            channel.closeFuture().sync();
        } finally {
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new HzlpClient("127.0.0.1",8080).run();
    }
}
