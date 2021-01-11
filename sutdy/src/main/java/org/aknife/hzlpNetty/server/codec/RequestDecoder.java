package org.aknife.hzlpNetty.server.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.java.Log;
import org.aknife.hzlpNetty.api.pojo.Request;

import java.util.List;

/**
 * 服务端对请求数据的解码,解析为Request对象
 * @ClassName RequestDecode
 * @Author HeZiLong
 * @Data 2021/1/7 17:25
 */
@Log
public class RequestDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf bytes, List<Object> list) throws Exception {
        Request request = new Request();

        byte[] temp = new byte[bytes.readableBytes()];
        bytes.writeBytes(temp);
        String[] data = new String(temp).split("\r\n");

        if(data.length == 0) {
            return;
        }
        String[] header = data[0].split(" ");
        if(data.length == 1){
            return;
        }
        // 这里是请求数据的主体，可能长度较大，所以要考虑粘包/半包问题
        String body = data[1];

        if(0 < header.length){
            request.setModel(Integer.parseInt(header[0]));
        }



//        log.info(new String(msg));
        list.add(request);
/*            request.setModel(byteBuf.readInt());
            long currentTimeMills = (byteBuf.readUnsignedInt() - 2208988800L) * 1000L;
            request.setDate(new Date(currentTimeMills));


            JSONArray array = new JSONArray();
            JSONObject msg = new JSONObject(new String(bytes));
            array.put(msg);
            request.setData(array);
            list.add(request);*/

    }
}
