package org.aknife.message.transmitter;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import org.aknife.business.base.packet.Packet;
import org.aknife.connection.initializer.SystemInitializer;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.user.model.User;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.connection.link.user.UserChannelConnection;
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
public class PacketTransmitterUtil {

    private static PacketTransmitterUtil transmitter = new PacketTransmitterUtil();

    public static PacketTransmitterUtil getInstance(){
        return transmitter;
    }

    private static ConcurrentHashMap<Integer, Channel> userChannel = new ConcurrentHashMap<>();

    private PacketTransmitterUtil(){
    }

    public static void initTransmitter(){
        ConcurrentHashMap<Channel, User> channelUser = UserChannelConnection.getAllUserChannel();
        for (Channel channel : channelUser.keySet()){
            PacketTransmitterUtil.userChannel.put(channelUser.get(channel).getUserID(), channel);
        }
    }

    /**
     * 向指定用户发送协议信息
     * @param user
     * @param o
     */
    public static void writePacket(User user, Packet o){
        Channel channel = userChannel.get(user.getUserID());
        if (channel == null){
            throw new GlobalException("该通道已断开");
        }
        Message message = new Message(PacketFixedConsts.getCodeByClass(o.getClass()), new Date(), o);
        channel.writeAndFlush(message);
    }

    /**
     * 向指定用户发送协议信息，并接受返回信息，如果没有则断开连接
     * @param user
     * @param o
     */
    public static void writePacketAndCloseIfNoResponse(User user, Packet o){
        Channel channel = userChannel.get(user.getUserID());
        if (channel == null){
            throw new GlobalException("该通道已断开");
        }
        Message message = new Message(PacketFixedConsts.getCodeByClass(o.getClass()), new Date(), o);
        channel.writeAndFlush(message).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

    public static void userNoticePacketTransmitter(Channel channel, User user){
        userChannel.put(user.getUserID(),channel);
    }

    public static void updateUserID(int oldID, int nowID){
        synchronized (transmitter){
            Channel channel = userChannel.get(oldID);
            userChannel.remove(oldID);
            userChannel.put(nowID, channel);
        }
    }

}
