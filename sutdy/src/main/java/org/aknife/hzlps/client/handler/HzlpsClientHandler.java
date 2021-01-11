package org.aknife.hzlps.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;

/**
 * @ClassName HzlpClientHandler
 * @Author HeZiLong
 * @Data 2021/1/8 14:36
 */
@Log
public class HzlpsClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        log.info("运行ClientHandler中的channelRead0");
        log.info(o.toString());
    }
}
