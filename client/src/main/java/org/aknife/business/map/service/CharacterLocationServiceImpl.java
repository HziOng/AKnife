package org.aknife.business.map.service;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.map.service.ICharacterLocationService;
import org.aknife.business.map.swing.SwingGameForm;
import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * 客户端角色地图位置业务
 * @ClassName CharacterLocationServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/21 10:16
 */
@Service
public class CharacterLocationServiceImpl implements ICharacterLocationService {

    @Override
    public void moveLocation() {
        JFrame jFrame = jFrameStack.peek();
        if (!gameForm.equals(jFrame.getTitle())){
            throw new GlobalException("该界面不是游戏界面，角色无法进行移动");
        }
        SwingGameForm form = (SwingGameForm) jFrame;
        form.setLocationText(form.getToLocation().toString());
        form.repaint();
    }

    @Override
    public void movePlaceFailure(String message) {
        JFrame jFrame = jFrameStack.peek();
        if (!gameForm.equals(jFrame.getTitle())){
            throw new GlobalException("该界面不是游戏界面，角色无法进行移动");
        }
        SwingGameForm form = (SwingGameForm) jFrame;
        form.setError(message);
        form.repaint();
    }
}
