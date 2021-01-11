package org.aknife.chat.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.extern.java.Log;

import java.util.List;

/**
 * 第二个运行的解码器，该解码器没有什么实际功能，只是为了探究解码器在执行过程中的一些问题，详情请查看plan/2021_1_7总结中的实验1
 * @ClassName SimpleChatServerNextDecoder
 * @Author HeZiLong
 * @Data 2021/1/7 12:14
 */
@Log
public class SimpleChatServerNextDecoder extends MessageToMessageDecoder<String>{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, String in, List<Object> out) throws Exception {
        log.info("运行至第二个解码器"+ in);
        out.add("110");
    }
}
