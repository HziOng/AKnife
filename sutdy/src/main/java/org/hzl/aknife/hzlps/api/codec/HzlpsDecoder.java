package org.hzl.aknife.hzlps.api.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.java.Log;
import org.hzl.aknife.hzlps.api.pojo.Message;
import org.hzl.aknife.hzlps.api.utils.ProtocolFixedData;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * Hzlp协议公用解码器
 * @ClassName HzlpDecoder
 * @Author HeZiLong
 * @Data 2021/1/8 13:02
 */
@Log
public class HzlpsDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//        log.info("进入公用解码器");
        byteBuf.markReaderIndex();
        log.info("总共："+byteBuf.readableBytes()+"字节");
        if(ProtocolFixedData.PACKAGE_HEADER == byteBuf.readInt()) {
            Message message = new Message();
            message.setModel(byteBuf.readInt());
            message.setStatus(byteBuf.readInt());
            message.setDate(new Date((byteBuf.readUnsignedInt() - 2208988800L) * 1000L));
            message.setSize(byteBuf.readInt());

            if(message.getSize() < 0){
                channelHandlerContext.close();
            }
            if(byteBuf.readableBytes() < message.getSize()){
                return;
            }

            byte[] temp = new byte[message.getSize()];
            byteBuf.readBytes(temp);
            JSONObject data = new JSONObject(new String(temp));
            String classCode = data.keys().next();
            Object o = data.get(classCode);
            message.setData(o);
            list.add(message);

        }
    }
}
