package org.aknife.hzlpNetty.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

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
