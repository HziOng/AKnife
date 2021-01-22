package org.aknife.business.character.service;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.manager.CharacterManager;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.character.service.IUserCharacterService;
import org.aknife.business.map.model.Location;
import org.aknife.business.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public UserCharacter getInitCharacter(User operaUser) {
        return characterManager.getCharacterByCharacterId(operaUser.getCharacterId());
    }

    @Override
    public HashMap<Integer, Location> getLocationFromUser(User operaUser) {
        ConcurrentHashMap<Integer, UserCharacter> characters = characterManager.getCharacterByUserId(operaUser);
        HashMap<Integer,Location> locations = new HashMap<>();
        for (Integer id : characters.keySet()){
            locations.put(id, characters.get(id).getLocation());
        }
        return locations;
    }


}
