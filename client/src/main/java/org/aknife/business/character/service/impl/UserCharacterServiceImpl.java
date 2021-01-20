package org.aknife.business.character.service.impl;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.character.service.UserCharacterService;
import org.aknife.business.user.model.User;
import org.aknife.business.user.swing.SwingGameForm;
import org.aknife.resource.ResourceManager;
import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * 用户角色操作业务
 * @ClassName UserCharacterServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/19 10:02
 */
@Service
public class UserCharacterServiceImpl implements UserCharacterService {

    @Override
    public void toCharacterInfo(UserCharacter character) {
        SwingGameForm form = new SwingGameForm();
        form.setUserText(character.getUsername());
        form.setMapText(ResourceManager.getMapNameByID(character.getMapID()));
        form.setLocationText(character.getLocation().toString());
        form.setMapImage(ResourceManager.getMapImageUrlByID(character.getMapID()));

        form.repaint();
        jFrameStack.push(form);
    }

    @Override
    public void switchMapAllCharacterFromUser(User user, int toMapID) {
        JFrame jFrame = jFrameStack.peek();
        if (!gameForm.equals(jFrame.getTitle())){
            throw new GlobalException("该界面不是游戏界面，无法切换地图");
        }
        SwingGameForm form = (SwingGameForm) jFrame;
        form.setMapText(ResourceManager.getMapNameByID(toMapID));
        form.setMapImage(ResourceManager.getMapImageUrlByID(toMapID));

        form.repaint();
    }

    @Override
    public void switchMapFailure(String message) {
        JFrame jFrame = jFrameStack.peek();
        if (!gameForm.equals(jFrame.getTitle())){
            throw new GlobalException("该界面不是游戏界面，无法显示切换地图错误信息");
        }
        SwingGameForm form = (SwingGameForm) jFrame;
        form.setError(message);

        form.repaint();
    }

}
