package org.aknife.business.base.controller;

import io.netty.channel.Channel;
import org.aknife.business.user.model.User;
import org.aknife.message.model.Message;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BaseController
 * @Author HeZiLong
 * @Data 2021/1/13 11:14
 */
public class BaseController {

    protected static User user;

    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    private ConcurrentHashMap<Channel, User> userChannel = null;


}
