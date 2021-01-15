package org.aknife.message.transmitter;

import io.netty.channel.Channel;
import org.aknife.SystemInitializer;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.user.model.User;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.link.user.UserChannelConnection;
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

    private static ConcurrentHashMap<Integer, Channel> userChannel = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<Integer, Method> protocolMap = null;


    private PacketTransmitter(){
    }

    public static void initTransmitter(SystemInitializer initializer){
        ConcurrentHashMap<Channel, User> channelUser = UserChannelConnection.getAllUserChannel();
        for (Channel channel : channelUser.keySet()){
            PacketTransmitter.userChannel.put(channelUser.get(channel).getUserID(), channel);
        }
        protocolMap = initializer.getProtocolMap();
    }

    public void writePacket(User user, Object o){
        Channel channel = userChannel.get(user.getUserID());
        if (channel == null){
            throw new GlobalException("该通道已断开");
        }
        Message message = new Message(PacketFixedConsts.getCodeByClass(o.getClass()), ProtocolFixedData.STATUS_OK, new Date(), o);
        channel.writeAndFlush(message);
    }

    public static void userNoticePacketTransmitter(Channel channel, User user){
        userChannel.put(user.getUserID(),channel);
    }

    public void updateUserID(int oldID, int nowID){
        synchronized (transmitter){
            Channel channel = userChannel.get(oldID);
            userChannel.remove(oldID);
            userChannel.put(nowID, channel);
        }
    }

}
