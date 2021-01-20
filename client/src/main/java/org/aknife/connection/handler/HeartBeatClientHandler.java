package org.aknife.connection.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;
import org.aknife.business.user.packet.account.SM_UserLogin;
import org.aknife.business.user.packet.account.SM_UserRegister;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.model.Message;
import org.aknife.business.user.packet.account.CM_UserHeart;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName HeartBeatClientHandler
 * @Author HeZiLong
 * @Data 2021/1/13 16:54
 */
public class HeartBeatClientHandler extends AbstractClientHandler {

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        // 用户如果发来的不是登录，也不是注册响应，同时用户还没有登录成功，那么用户将没有权限操作进行其他操作
        if ((user == null) && (msg.getType() != getCode(SM_UserLogin.class) || msg.getType() != getCode(SM_UserRegister.class))){
            // 这里要进行弹框，显示没有权限操作。
        }
        if (msg.getType() == PacketFixedConsts.getCodeByClass(CM_UserHeart.class)){
            heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx),0,5000, TimeUnit.MILLISECONDS);
            return;
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (heartBeat != null){
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }

    private class HeartBeatTask implements Runnable{

        private final ChannelHandlerContext ctx;

        public HeartBeatTask(ChannelHandlerContext ctx){
            this.ctx = ctx;
        }

        @Override
        public void run() {
            String heartBeat = "I am ok";
            System.out.println("Client send heart beat message to server: ----->"+heartBeat);
            Message message = new Message();
            message.setType(PacketFixedConsts.getCodeByClass(CM_UserHeart.class));
            ctx.writeAndFlush(message);
        }
    }
}
