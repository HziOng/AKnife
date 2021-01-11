package org.aknife.message.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.java.Log;
import org.aknife.message.model.Message;
import org.aknife.util.ProtocolFixedData;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * 解码器，将网络传输过来的ByteBuf转化为Handler可以识别的Message
 * @ClassName GameMessageDecoder
 * @Author HeZiLong
 * @Data 2021/1/11 10:25
 */
@Log
public class GameMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        in.markReaderIndex();
        if(ProtocolFixedData.PACKAGE_HEADER == in.readInt()) {
            Message message = new Message();
            message.setType(in.readInt());
            message.setStatus(in.readInt());
            message.setDate(new Date((in.readUnsignedInt() - 2208988800L) * 1000L));
            message.setSize(in.readInt());

            if(message.getSize() < 0){
                channelHandlerContext.close();
            }
            if(in.readableBytes() < message.getSize()){
                return;
            }

            byte[] temp = new byte[message.getSize()];
            in.readBytes(temp);
            String strData = new String(temp);
            message.setData(strData);
            out.add(message);
        }
    }
}
