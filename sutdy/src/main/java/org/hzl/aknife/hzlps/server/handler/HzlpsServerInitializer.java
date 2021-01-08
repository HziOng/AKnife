package org.hzl.aknife.hzlps.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.hzl.aknife.hzlps.api.codec.HzlpsDecoder;
import org.hzl.aknife.hzlps.api.codec.HzlpsEncoder;

/**
 * 服务端Channel初始化器
 * @ClassName HzlpServerInitializer
 * @Author HeZiLong
 * @Data 2021/1/8 14:29
 */
public class HzlpsServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("decoder", new HzlpsDecoder());
        pipeline.addLast("encoder", new HzlpsEncoder());
        pipeline.addLast("handler", new HzlpsServerHandler());
    }
}
