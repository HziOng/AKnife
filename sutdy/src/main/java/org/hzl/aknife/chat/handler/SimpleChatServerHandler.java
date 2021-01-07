package org.hzl.aknife.chat.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.java.Log;

/**
 * 服务端聊天室处理器
 * @ClassName SimpleChatClient
 * @Author HeZiLong
 * @Data 2021/1/6 12:29
 */
@Log
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 用户加入
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();
        for (Channel channel : channels){
            channel.writeAndFlush("【server】 - " + inComing.remoteAddress() + " 加入\n");
        }
        ctx.writeAndFlush("");
        channels.add(inComing);
    }

    /**
     * 用户离开
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel inComing =  ctx.channel();
        for(Channel channel : channels){
            channel.writeAndFlush("【server】 - " + inComing.remoteAddress() + " 离开\n");
        }
        channels.remove(inComing);
    }

    /**
     * 用户在线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel inComing  = ctx.channel();
        System.out.println("SimpleChatClient: "+inComing.remoteAddress()+" 在线");
    }

    /**
     * 用户掉线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel inComing  = ctx.channel();
        System.out.println("SimpleChatClient: "+inComing.remoteAddress()+" 掉线");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        log.info(s);
        Channel inComing = channelHandlerContext.channel();
        for (Channel channel : channels){
            if(channel != inComing){
                channel.writeAndFlush("【"+ inComing.remoteAddress() +"】"+ s + "\n");
            }else {
                channel.writeAndFlush("【you】"+ s + "\n");
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
