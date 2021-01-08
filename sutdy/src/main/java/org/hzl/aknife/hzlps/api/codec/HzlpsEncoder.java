package org.hzl.aknife.hzlps.api.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.java.Log;
import org.hzl.aknife.hzlps.api.pojo.Message;
import org.hzl.aknife.hzlpNetty.api.util.ConsantUtil;

import java.util.List;

/**
 * Hzlp协议公用编码器
 * @ClassName HzlpEncoder
 * @Author HeZiLong
 * @Data 2021/1/8 13:01
 */
@Log
public class HzlpsEncoder extends MessageToMessageEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, List<Object> list) throws Exception {
//        log.info("进入公用编码器");

        ByteBuf buf = Unpooled.buffer();
        byte[] data = message.getData().toString().getBytes();
        // 向协议内容中添加请求头
        buf.writeInt(ConsantUtil.PACKAGE_HEADER);
        buf.writeInt(message.getModel());
        buf.writeInt(message.getStatus());
        buf.writeInt((int) message.getDate().getTime());
        buf.writeInt(data.length);
        buf.writeBytes(data);

        list.add(buf);
    }
}
