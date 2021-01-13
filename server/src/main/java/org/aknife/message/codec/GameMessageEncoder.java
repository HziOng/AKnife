package org.aknife.message.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.model.Message;
import org.aknife.constant.ProtocolFixedData;

import java.util.List;

/**
 * @ClassName GameMessageEncoder
 * @Author HeZiLong
 * @Data 2021/1/11 10:26
 */
public class GameMessageEncoder extends MessageToMessageEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, List<Object> list) throws Exception {
        ByteBuf buf = Unpooled.buffer();
        String jsonData = JSON.toJSONString(message.getData());
        byte[] data = jsonData.getBytes();
        // 向协议内容中添加请求头
        buf.writeInt(ProtocolFixedData.PACKAGE_HEADER);
        buf.writeInt(message.getType());
        buf.writeInt(message.getStatus());
        buf.writeInt((int) message.getDate().getTime());
        buf.writeInt(data.length);
        buf.writeBytes(data);

        list.add(buf);
    }
}
