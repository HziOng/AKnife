package org.aknife.message.transmitter;

import io.netty.channel.Channel;
import org.aknife.SystemInitializer;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.user.model.User;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.message.model.Message;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 协议发送器，服务端controller层发送协议给客户端的工具
 * @ClassName PacketTransmitter
 * @Author HeZiLong
 * @Data 2021/1/15 14:48
 */
public class PacketTransmitter {

    private static PacketTransmitter transmitter = new PacketTransmitter();

    public static PacketTransmitter getInstance(){
        return transmitter;
    }

    private static Channel channel;


    private PacketTransmitter(){
    }

    public static void initTransmitter(Channel nowChannel){
        channel = nowChannel;
    }

    public static void writePacket(Object o){
        if (channel == null){
            throw new GlobalException("该通道已断开");
        }
        Message message = new Message(PacketFixedConsts.getCodeByClass(o.getClass()), ProtocolFixedData.STATUS_OK, new Date(), o);
        channel.writeAndFlush(message);
    }
}
