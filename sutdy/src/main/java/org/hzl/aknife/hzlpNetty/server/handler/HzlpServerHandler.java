package org.hzl.aknife.hzlpNetty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;
import org.hzl.aknife.hzlpNetty.api.pojo.Request;
import org.hzl.aknife.hzlpNetty.api.pojo.Response;
import org.hzl.aknife.hzlpNetty.api.util.ConsantUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

/**
 * Hzlp是自定义的一种类似于http的协议
 * @ClassName HzlpServerHandler
 * @Author HeZiLong
 * @Data 2021/1/7 17:53
 */
@Log
public class HzlpServerHandler extends SimpleChannelInboundHandler<Request> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) throws Exception {
        log.info(request.toString());
        Response response = new Response();



        response.setModel(ConsantUtil.MODEL_ONE);
        response.setStatus(ConsantUtil.STATUS_OK);
        response.setDate(new Date());
        JSONArray array = new JSONArray();
        JSONObject msg = new JSONObject();
        msg.put("result","创建成功");
        array.put(msg);
        response.setData(array);

        channelHandlerContext.writeAndFlush(response);
    }
}
