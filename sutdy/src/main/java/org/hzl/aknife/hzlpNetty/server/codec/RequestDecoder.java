package org.hzl.aknife.hzlpNetty.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.hzl.aknife.hzlpNetty.api.pojo.Request;
import org.hzl.aknife.hzlpNetty.api.util.ConsantUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * 服务端对请求数据的解码,解析为Request对象
 * @ClassName RequestDecode
 * @Author HeZiLong
 * @Data 2021/1/7 17:25
 */
public class RequestDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (ConsantUtil.PACKAGE_HEADER == byteBuf.readInt()) {
            Request request = new Request();

            request.setModel(byteBuf.readInt());
            long currentTimeMills = (byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
            request.setDate(new Date(currentTimeMills));
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            JSONArray array = new JSONArray();
            JSONObject msg = new JSONObject(new String(bytes));
            array.put(msg);
            request.setData(array);
            list.add(request);
        }
    }
}
