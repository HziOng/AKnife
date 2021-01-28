package org.aknife.connection.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.aknife.business.user.model.User;
import org.aknife.business.user.packet.CM_UserLogin;
import org.aknife.business.user.packet.CM_UserOffLine;
import org.aknife.business.user.packet.CM_UserRegister;
import org.aknife.business.user.util.UserUtil;
import org.aknife.message.model.Message;
import org.aknife.message.transmitter.PacketTransmitterUtil;

/**
 * @ClassName AccessControlHandler
 * @Author HeZiLong
 * @Data 2021/1/27 11:06
 */
public class AccessControlHandler extends AbstractServerHandler{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        Channel channel = channelHandlerContext.channel();
        User nowUser = userChannel.get(channel);
        // user-channel映射处理
        if(nowUser == null){
            // 如果发送的协议不是登录，注册，同时后台还没有该用户数据，让用户进行重新登录
            if(message.getType() != getClassCode(CM_UserLogin.class) &&
                    message.getType() != getClassCode(CM_UserRegister.class)){
                    // 这里发送重连协议
            }
            nowUser = new User("未登录用户", "未确定密码");
            // 如果用户还不存在，先生成临时ID
            nowUser.setUserID(UserUtil.getUUID());
            userChannel.put(channel, nowUser);
            PacketTransmitterUtil.userNoticePacketTransmitter(channel, nowUser);
        }
        if (message.getType() == getClassCode(CM_UserOffLine.class)){
            userChannel.remove(channel);
        }
        channelHandlerContext.fireChannelRead(message);
    }
}
