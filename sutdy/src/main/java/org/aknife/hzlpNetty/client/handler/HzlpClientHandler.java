package org.aknife.hzlpNetty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;
import org.aknife.hzlpNetty.api.pojo.Response;

/**
 * Hzlp是自定义的一种类似于http的协议,这里是客户端接收到服务端响应的处理内容
 * @ClassName HzlpClientHandler
 * @Author HeZiLong
 * @Data 2021/1/7 17:58
 */
@Log
public class HzlpClientHandler extends SimpleChannelInboundHandler<Response> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Response response) throws Exception {
        log.info(response.toString());
    }
}
