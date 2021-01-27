package org.aknife.connection.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.java.Log;
import org.aknife.business.user.packet.CM_UserHeart;
import org.aknife.business.user.packet.SM_UserHeart;
import org.aknife.business.user.packet.SM_UserLogin;
import org.aknife.business.user.packet.SM_UserRegister;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.model.Message;
import org.aknife.message.transmitter.PacketTransmitterUtil;

/**
 * 客户端心跳检测
 * @ClassName HeartBeatClientHandler
 * @Author HeZiLong
 * @Data 2021/1/13 16:54
 */
@Log
public class HeartBeatClientHandler extends AbstractClientHandler {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        // 用户如果发来的不是登录，也不是注册响应，同时用户还没有登录成功，那么用户将没有权限操作进行其他操作
        if ((user == null) && (msg.getType() != getCode(SM_UserLogin.class) || msg.getType() != getCode(SM_UserRegister.class))){
            // 这里要进行弹框，显示没有权限操作。
        }
        if (msg.getType() == PacketFixedConsts.getCodeByClass(SM_UserHeart.class)){
            log.info(ctx.channel().remoteAddress() + "===>client: " + " server is connecting");
            return;
        }
        ctx.fireChannelRead(msg);
    }

    /**
     * 如果4s没有收到写请求，则向服务端发送心跳请求
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if(IdleState.WRITER_IDLE.equals(event.state())) {
                PacketTransmitterUtil.writePacketAndCloseIfNoResponse(new CM_UserHeart());
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("server channelActive");
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("server is close");
    }
}
