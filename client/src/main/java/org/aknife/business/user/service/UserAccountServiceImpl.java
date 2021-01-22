package org.aknife.business.user.service;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.model.Location;
import org.aknife.business.user.model.User;
import org.aknife.business.user.service.UserAccountService;
import org.aknife.business.user.swing.SwingLoginForm;
import org.aknife.business.user.swing.SwingRegisterForm;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashMap;

/**
 * 用户账号操作响应协议处理业务
 * @ClassName UserAccountServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/18 18:31
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Override
    public void updateUser(User now, int mapID, int characterId) {
        user.setMapId(mapID);
        user.setUserID(now.getUserID());
        user.setCharacterId(characterId);
        user.setUsername(now.getUsername());
        user.setCharacterIds(now.getCharacterIds());
        for (Integer id : user.getCharacterIds()){
            UserCharacter character = new UserCharacter(id,user.getUsername(),new Location(10,10,0));
            characters.put(id, character);
        }
    }

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

    @Override
    public UserCharacter getInitCharacter() {
        if (user.getUserID() == 0){
            return null;
        }
        return characters.get(user.getCharacterId());
    }
}
