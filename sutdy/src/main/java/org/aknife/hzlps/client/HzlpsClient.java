package org.aknife.hzlps.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.java.Log;
import org.aknife.hzlps.api.pojo.User;
import org.aknife.hzlps.client.handler.HzlpsClientInitializer;
import org.aknife.hzlpNetty.api.util.ConsantUtil;
import org.json.JSONObject;

/**
 * @ClassName HzlpsClient
 * @Author HeZiLong
 * @Data 2021/1/8 14:43
 */
@Log
public class HzlpsClient {

    private String host;
    private int port;

    public HzlpsClient(String host, int port){
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
                    .handler(new HzlpsClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();

            for(int i=0; i<100; i++){

                // 设置请求体
                JSONObject msg = new JSONObject();
                User user = new User();
                user.setUserID(1);
                user.setName("贺子龙");
                user.setAge(i);
                msg.put("1",user);

                ByteBuf buf = Unpooled.buffer(20+msg.toString().length());

                // 向协议内容中添加请求头
                buf.writeInt(ConsantUtil.PACKAGE_HEADER);
                buf.writeInt(i);
                buf.writeInt(i+1);
                buf.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

                byte[] data = msg.toString().getBytes();
                buf.writeInt(data.length);
                buf.writeBytes(data);
                log.info("发出了"+buf.readableBytes()+"字节");
                channel.writeAndFlush(buf);
            }

            channel.closeFuture().sync();
        } finally {
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new HzlpsClient("127.0.0.1",8081).run();
    }
}
