package org.aknife.business.character.service;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.character.service.UserCharacterService;
import org.aknife.business.map.swing.SwingGameForm;
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
    public void toCharacterInfo(UserCharacter character) {
        SwingGameForm form = new SwingGameForm();
        form.setUser(user);
        form.setUserText(character.getUsername());

        form.repaint();
        jFrameStack.push(form);
    }

}
