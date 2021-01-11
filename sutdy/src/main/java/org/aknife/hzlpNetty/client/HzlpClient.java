package org.aknife.hzlpNetty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.aknife.hzlpNetty.api.util.ConsantUtil;
import org.aknife.hzlpNetty.client.handler.HzlpClientInitializer;
import org.json.JSONArray;
import org.json.JSONObject;

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
            // 先将协议内容转化为字符串，之后会转化为byte数组，使用上面的buf发送出去
            StringBuffer strBuf = new StringBuffer();
            JSONArray jsonArray = new JSONArray();

            // 向协议内容中添加请求头
            strBuf.append(ConsantUtil.PACKAGE_HEADER+" ")
                    .append(ConsantUtil.MODEL_ONE+" ")
                    .append((int) (System.currentTimeMillis() / 1000L + 2208988800L))
                    .append("\r\n");

            // 向协议内容中添加方法请求体
            JSONObject msg = new JSONObject();
            msg.put("name","贺子龙");
            msg.put("age","20");
            msg.put("id","00001");
            jsonArray.put(msg);
            strBuf.append(jsonArray.toString());
            buf.writeBytes(strBuf.toString().getBytes());

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
