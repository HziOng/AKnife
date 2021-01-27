package org.aknife.connection.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.aknife.message.codec.GameMessageDecoder;
import org.aknife.message.codec.GameMessageEncoder;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName GameClientInitializer
 * @Author HeZiLong
 * @Data 2021/1/11 10:58
 */
public class GameClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
        pipeline.addLast("decoder", new GameMessageDecoder());
        pipeline.addLast("encoder", new GameMessageEncoder());
        pipeline.addLast("heartBeatHandler", new HeartBeatClientHandler());
        pipeline.addLast("controlHandler", new GameClientControlHandler());
    }
}
