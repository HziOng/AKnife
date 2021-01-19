package org.aknife.business.base.controller;

import io.netty.channel.Channel;
import org.aknife.business.user.model.User;
import org.aknife.business.base.packet.Packet;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.message.model.Message;
import org.aknife.message.transmitter.PacketTransmitter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BaseController
 * @Author HeZiLong
 * @Data 2021/1/13 11:14
 */
public class BaseController {

    private Message message;

    private PacketTransmitter transmitter = PacketTransmitter.getInstance();;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    private ConcurrentHashMap<Channel, User> userChannel = null;

    public int getClassCode(Class clazz){
        return PacketFixedConsts.getCodeByClass(clazz);
    }

    public void writePacket(User operaUser, Packet packet){
        transmitter.writePacket(operaUser,packet);
    }

    /**
     * 更新协议发送器中临时用户的信息
     * @param oldID
     * @param nowID
     */
    public void updatePacketTransmitter(int oldID, int nowID){
        transmitter.updateUserID(oldID,nowID);
    }


}
