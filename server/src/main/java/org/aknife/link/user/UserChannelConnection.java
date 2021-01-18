package org.aknife.link.user;

import io.netty.channel.Channel;
import org.aknife.business.user.model.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户存储Channel-User的关系映射
 * @ClassName UserConnection
 * @Author HeZiLong
 * @Data 2021/1/12 11:08
 */
public class UserChannelConnection {

    private static ConcurrentHashMap<Channel, User> userChannel = null;

    static {
        /**
         * 这里要在hibernate中将数据加载出来
         */
        userChannel = new ConcurrentHashMap<>();
    }

    public static ConcurrentHashMap<Channel, User> getAllUserChannel(){
        if (userChannel == null) {
            userChannel = new ConcurrentHashMap<>();
        }
        return userChannel;
    }

    /**
     * 找到指定channel对应的用户
     * @return
     */
    public static User getUserByChannel(Channel channel){
        return userChannel.get(channel);
    }

    /**
     * 添加channel和用户账号的映射关系
     * @param channel
     * @param user
     */
    public static void addUserChannel(Channel channel, User user){
        userChannel.put(channel, user);
    }
}
