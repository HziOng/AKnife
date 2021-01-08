package org.hzl.aknife.hzlpNetty.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.hzl.aknife.hzlpNetty.api.pojo.Request;
import org.hzl.aknife.hzlpNetty.api.pojo.Response;
import org.hzl.aknife.hzlpNetty.api.util.ConsantUtil;

import java.util.List;

/**
 * 客户端对请求数据的编码
 * @ClassName RequestEncode
 * @Author HeZiLong
 * @Data 2021/1/7 17:23
 */
public class RequestEncoder extends MessageToMessageEncoder<Byte[]> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,Byte[] byteBuf, List<Object> out) throws Exception {final ByteBuf buf = Unpooled.buffer();

        System.out.println(byteBuf);
        out.add(byteBuf);
    }
}
