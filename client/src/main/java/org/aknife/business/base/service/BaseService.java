package org.aknife.business.base.service;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.user.model.User;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端业务接口
 * @ClassName BaseService
 * @Author HeZiLong
 * @Data 2021/1/15 18:20
 */
public interface BaseService {

    String loginForm = "Login Example";

    String registerForm = "Register Example";

    String gameForm = "Game Example";

    User user = new User();

    Stack<JFrame> jFrameStack = new Stack<JFrame>();

    ConcurrentHashMap<Integer, UserCharacter> characters = new ConcurrentHashMap<>();

    ConcurrentHashMap<Integer, User> otherUser = new ConcurrentHashMap<>();

}
