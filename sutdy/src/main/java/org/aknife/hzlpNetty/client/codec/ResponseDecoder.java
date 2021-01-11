package org.aknife.hzlpNetty.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.aknife.hzlpNetty.api.pojo.Response;

import java.util.List;

/**
 * 客户端对响应数据解码
 * @ClassName ResponseDecoder
 * @Author HeZiLong
 * @Data 2021/1/7 17:23
 */
public class ResponseDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Response response = new Response();

        list.add(response);
    }
}
