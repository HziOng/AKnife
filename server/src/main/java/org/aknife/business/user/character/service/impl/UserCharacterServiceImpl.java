package org.aknife.business.user.character.service.impl;

import org.aknife.business.user.character.manager.CharacterManager;
import org.aknife.business.user.character.model.UserCharacter;
import org.aknife.business.user.character.service.IUserCharacterService;
import org.aknife.business.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserCharacterServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/19 10:00
 */
@Service
public class UserCharacterServiceImpl implements IUserCharacterService {

    private CharacterManager characterManager;

    @Autowired
    public void setCharacterManager(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    @Override
    public void switchMap(UserCharacter character, int toMapId) {
        character.setMapID(toMapId);
        characterManager.updateCharacter(character);
    }

    @Override
    public UserCharacter getInitCharacter(User operaUser) {
        return characterManager.getCharacterByCharacterId(operaUser.getCharacterId());
    }


}
