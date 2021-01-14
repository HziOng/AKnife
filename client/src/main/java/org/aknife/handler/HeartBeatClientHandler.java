package org.aknife.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.ScheduledFuture;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.model.Message;
import org.aknife.business.user.packet.CM_UserHeart;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName HeartBeatClientHandler
 * @Author HeZiLong
 * @Data 2021/1/13 16:54
 */
public class HeartBeatClientHandler extends SimpleChannelInboundHandler<Message> {

    private volatile ScheduledFuture<?> heartBeat;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
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
