package org.hzl.aknife.hzlpNetty.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.hzl.aknife.hzlpNetty.api.pojo.Request;
import org.hzl.aknife.hzlpNetty.client.codec.RequestEncoder;
import org.hzl.aknife.hzlpNetty.client.codec.ResponseDecoder;
import org.hzl.aknife.hzlpNetty.server.codec.RequestDecoder;
import org.hzl.aknife.hzlpNetty.server.codec.ResponseEncoder;

/**
 * @ClassName HttpClientInitializer
 * @Author HeZiLong
 * @Data 2021/1/7 16:48
 */
public class HzlpClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

//        pipeline.addLast("encoder",new RequestEncoder());
        pipeline.addLast("handler",new HzlpClientHandler());

    }
}
