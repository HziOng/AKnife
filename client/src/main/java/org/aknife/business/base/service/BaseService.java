package org.aknife.business.base.service;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Stack;

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

    Stack<JFrame> jFrameStack = new Stack<JFrame>();
}
