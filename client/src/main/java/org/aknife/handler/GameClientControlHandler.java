package org.aknife.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;

/**
 * @ClassName GameClientControlHandler
 * @Author HeZiLong
 * @Data 2021/1/11 10:58
 */
@Log
public class GameClientControlHandler extends SimpleChannelInboundHandler {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        log.info("运行至客户端控制处理程序ClientControlHandler中，得到了"+o);
    }
}
