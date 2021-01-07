package org.hzl.aknife.hzlpNetty.server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.hzl.aknife.hzlpNetty.api.pojo.Request;
import org.hzl.aknife.hzlpNetty.client.codec.ResponseDecoder;
import org.hzl.aknife.hzlpNetty.server.codec.RequestDecoder;
import org.hzl.aknife.hzlpNetty.server.codec.ResponseEncoder;

/**
 * @ClassName HttpServerInitializer
 * @Author HeZiLong
 * @Data 2021/1/7 16:48
 */
public class HzlpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("decoder", new RequestDecoder());
        pipeline.addLast("encoder", new ResponseEncoder());
        pipeline.addLast("handler", new HzlpServerHandler());
    }
}
