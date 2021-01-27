package org.aknife.message.transmitter;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.model.Message;

import java.util.Date;

/**
 * 协议发送器，服务端controller层发送协议给客户端的工具
 * @ClassName PacketTransmitter
 * @Author HeZiLong
 * @Data 2021/1/15 14:48
 */
public class PacketTransmitterUtil {

    private static PacketTransmitterUtil transmitter = new PacketTransmitterUtil();

    public static PacketTransmitterUtil getInstance(){
        return transmitter;
    }

    private static Channel channel;


    private PacketTransmitterUtil(){
    }

    public static void initTransmitter(Channel nowChannel){
        channel = nowChannel;
    }

    public static void writePacket(Object o){
        if (channel == null){
            throw new GlobalException("该通道已断开");
        }
        Message message = new Message(PacketFixedConsts.getCodeByClass(o.getClass()), new Date(), o);
        channel.writeAndFlush(message);
    }

    /**
     * 向指定用户发送协议信息，并接受返回信息，如果没有则断开连接
     * @param o
     */
    public static void writePacketAndCloseIfNoResponse(Object o){
        if (channel == null){
            throw new GlobalException("该通道已断开");
        }
        Message message = new Message(PacketFixedConsts.getCodeByClass(o.getClass()), new Date(), o);
        channel.writeAndFlush(message).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }
}
