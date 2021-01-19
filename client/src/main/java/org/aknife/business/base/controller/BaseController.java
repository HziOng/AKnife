package org.aknife.business.base.controller;

import io.netty.channel.Channel;
import org.aknife.business.user.character.model.UserCharacter;
import org.aknife.business.user.model.User;
import org.aknife.message.model.Message;

import javax.swing.*;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BaseController
 * @Author HeZiLong
 * @Data 2021/1/13 11:14
 */
public class BaseController {

    protected static User user = new User();

    protected static ConcurrentHashMap<Integer, UserCharacter> characters = new ConcurrentHashMap<>();

}
