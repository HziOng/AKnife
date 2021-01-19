package org.aknife.business.user.service.impl;

import org.aknife.business.user.service.UserAccountService;
import org.aknife.business.user.swing.SwingLoginForm;
import org.aknife.business.user.swing.SwingRegisterForm;
import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * 用户账号操作响应协议处理业务
 * @ClassName UserAccountServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/18 18:31
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Override
    public void loginFailure(String username, String message) {
        JFrame jFrame = jFrameStack.peek();
        if (!loginForm.equals(getTitle(jFrame))){
            return;
        }
        SwingLoginForm form = (SwingLoginForm) jFrame;
        form.setUsername(username);
        form.setError(message);
        form.repaint();
    }

    private String getTitle(JFrame jFrame) {
        return jFrame.getTitle();
    }

    @Override
    public void toLoginSwing(String username) {
        SwingLoginForm form = new SwingLoginForm();
        form.setUsername(username);
        form.repaint();
    }

    @Override
    public void closeLoginSwing() {
        JFrame frame = jFrameStack.peek();
        if (loginForm.equals(getTitle(frame))){
            jFrameStack.pop().dispose();
        }
    }

    @Override
    public void registerFailure(String username, String data) {
        JFrame jFrame = jFrameStack.peek();
        if (!registerForm.equals(getTitle(jFrame))){
            return;
        }
        SwingRegisterForm form = (SwingRegisterForm) jFrame;
        form.setUsername(username);
        form.setError(data);
        form.repaint();
    }

    @Override
    public void closeRegisterSwing() {
        JFrame frame = jFrameStack.peek();
        if (registerForm.equals(getTitle(frame))){
            jFrameStack.pop().dispose();
        }
    }
}
