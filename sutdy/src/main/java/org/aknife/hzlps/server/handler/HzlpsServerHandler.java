package org.aknife.hzlps.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;
import org.aknife.hzlps.api.pojo.Location;
import org.aknife.hzlps.api.pojo.Message;
import org.aknife.hzlps.api.utils.ProtocolFixedData;
import org.json.JSONObject;

import java.util.Date;

/**
 * 服务端主要
 * @ClassName HzlpServerHandler
 * @Author HeZiLong
 * @Data 2021/1/8 14:31
 */
@Log
public class HzlpsServerHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        log.info("运行ServerHandler中的channelRead0");

        log.info(o.toString());

        Message message = new Message();
        message.setModel(ProtocolFixedData.MODEL_TWO);
        message.setStatus(ProtocolFixedData.STATUS_OK);
        message.setDate(new Date(System.currentTimeMillis()));

        Location location = new Location();
        location.setArea(110);
        location.setPlace(120);
        location.setX(119);
        location.setY(911);
        JSONObject msg = new JSONObject();
        msg.put("2",location);
        message.setSize(msg.toString().getBytes().length);
        message.setData(msg);

        channelHandlerContext.writeAndFlush(message);
    }
}
