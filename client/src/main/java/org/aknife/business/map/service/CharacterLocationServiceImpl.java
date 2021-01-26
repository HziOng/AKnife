package org.aknife.business.map.service;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.model.Location;
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

    private SwingGameForm checkSwingForm(){
        JFrame jFrame = jFrameStack.peek();
        if (!gameForm.equals(jFrame.getTitle())){
            throw new GlobalException("该界面不是游戏界面，无法切换地图");
        }
        return (SwingGameForm) jFrame;
    }

    @Override
    public void moveLocation() {
        SwingGameForm form = checkSwingForm();
        form.setLocationText(form.getToLocation().toString());
        form.repaint();
    }

    @Override
    public void movePlaceFailure(String message) {
        SwingGameForm form = checkSwingForm();
        form.setError(message);
        form.repaint();
    }

    @Override
    public void otherMoveLocation(int characterId, Location toLocation) {
        SwingGameForm form = checkSwingForm();
        UserCharacter character = characters.get(characterId);
        character.setLocation(toLocation);
        form.addMessage("用户【" + character.getUsername() + "】的角色 ["+ characterId +"] 移动到位置: "+toLocation);
    }
}
