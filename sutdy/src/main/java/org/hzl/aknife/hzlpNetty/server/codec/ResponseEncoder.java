package org.hzl.aknife.hzlpNetty.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.string.StringEncoder;
import org.hzl.aknife.hzlpNetty.api.pojo.Response;
import org.hzl.aknife.hzlpNetty.api.util.ConsantUtil;

import java.util.List;

/**
 * 服务端对响应数据的编码，将Response解析为ByteBuf
 * @ClassName ResponseEncode
 * @Author HeZiLong
 * @Data 2021/1/7 17:24
 */
public class ResponseEncoder extends MessageToMessageEncoder<Response> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Response response, List<Object> out) throws Exception {
        ByteBuf buf = Unpooled.buffer();

        buf.writeInt(ConsantUtil.PACKAGE_HEADER);
        buf.writeInt(response.getModel());
        buf.writeInt(response.getStatus());
        buf.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
        buf.writeBytes(response.getData().toString().getBytes());

        out.add(buf);
    }
}
