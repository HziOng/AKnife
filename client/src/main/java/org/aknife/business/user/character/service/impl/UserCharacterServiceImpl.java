package org.aknife.business.user.character.service.impl;

import org.aknife.business.user.character.model.UserCharacter;
import org.aknife.business.user.character.service.UserCharacterService;
import org.aknife.business.user.swing.SwingGameForm;
import org.springframework.stereotype.Service;

/**
 * 用户角色操作业务
 * @ClassName UserCharacterServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/19 10:02
 */
@Service
public class UserCharacterServiceImpl implements UserCharacterService {

    @Override
    public int toCharacterInfo(UserCharacter character) {
        SwingGameForm form = new SwingGameForm();
        form.getUserText().setText(character.getUserName());
        form.getMapText().setText(character.getMapName());
        form.getLocationText().setText(character.getLocation().toString());
        form.repaint();
        jFrameStack.push(form);
        return 0;
    }
}
