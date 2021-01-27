package org.aknife.connection.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.extern.java.Log;
import org.aknife.business.user.packet.*;
import org.aknife.business.user.util.UserUtil;
import org.aknife.message.model.Message;
import org.aknife.business.user.model.User;
import org.aknife.message.transmitter.PacketTransmitterUtil;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务端心跳检测
 * @ClassName HeartBeatHandler
 * @Author HeZiLong
 * @Data 2021/1/13 15:53
 */
@Log
public class HeartBeatServerHandler extends AbstractServerHandler {

    public HeartBeatServerHandler(){
        super();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        Channel channel = channelHandlerContext.channel();
        User nowUser = userChannel.get(channel);
        if (message.getData() instanceof CM_UserHeart){
            log.info(channel.remoteAddress() + "===>server: " + nowUser.getUserID() + " is connected");
            PacketTransmitterUtil.writePacket(nowUser, new SM_UserHeart());
            return;
        }
        channelHandlerContext.fireChannelRead(message);
    }

    /**
     * 如果5s没有读请求，则向客户端发送心跳
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            Channel channel = ctx.channel();
            User nowUser = userChannel.get(channel);
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleState.READER_IDLE.equals((event.state()))) {
                PacketTransmitterUtil.writePacketAndCloseIfNoResponse(nowUser, new SM_UserHeart());
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("client channelActive: "+userChannel.get(ctx.channel()).getUserID());
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Client is close: "+userChannel.get(ctx.channel()).getUserID());
    }
}
