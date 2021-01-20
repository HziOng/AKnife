package org.aknife.business.character.service.impl;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.manager.CharacterManager;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.character.service.IUserCharacterService;
import org.aknife.business.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

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
    public void switchMapAllCharacter(User operaUser, int toMapId) {
        ConcurrentHashMap<Integer, UserCharacter> characterMap = characterManager.getCharacterByUserId(operaUser);
        if (characterMap == null){
            throw new GlobalException("异常，用户未拥有角色");
        }
        for (UserCharacter character : characterMap.values()){
            character.setMapID(toMapId);
        }
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
