package org.hzl.aknife.hzlps.client.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.hzl.aknife.hzlps.api.codec.HzlpsDecoder;
import org.hzl.aknife.hzlps.api.codec.HzlpsEncoder;

/**
 * 客户端Channel初始化器
 * @ClassName HzlpClientInitializer
 * @Author HeZiLong
 * @Data 2021/1/8 14:30
 */
public class HzlpsClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("decoder", new HzlpsDecoder());
        pipeline.addLast("encoder", new HzlpsEncoder());
        pipeline.addLast("handler", new HzlpsClientHandler());
    }
}
