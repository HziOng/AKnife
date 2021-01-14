package org.aknife.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.aknife.message.codec.GameMessageDecoder;
import org.aknife.message.codec.GameMessageEncoder;

/**
 * @ClassName GameClientInitializer
 * @Author HeZiLong
 * @Data 2021/1/11 10:58
 */
public class GameClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("decoder", new GameMessageDecoder());
        pipeline.addLast("encoder", new GameMessageEncoder());
        pipeline.addLast("readTimeOutHandler", new ReadTimeoutHandler(120));
        pipeline.addLast("heartBeatHandler", new HeartBeatClientHandler());
        pipeline.addLast("controlHandler", new GameClientControlHandler());
    }
}
