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
public class RequestEncoder extends MessageToMessageEncoder<Request> {


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Request request, List<Object> out) throws Exception {
        final ByteBuf buf = Unpooled.buffer();

        buf.writeInt(ConsantUtil.PACKAGE_HEADER);
        buf.writeInt(request.getModel());
        buf.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        buf.writeBytes(request.getData().toString().getBytes());

        out.add(buf);
    }
}
