package org.aknife.business.map.service;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.manager.CharacterManager;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.manager.GameMapManager;
import org.aknife.business.map.model.Location;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.business.user.manager.UserManager;
import org.aknife.business.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 服务端游戏地图业务处理
 * @ClassName MapServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/20 15:13
 */
@Service
public class GameMapServiceImpl implements IGameMapService {

    private GameMapManager mapManager;

    private CharacterManager characterManager;

    @Autowired
    public void setCharacterManager(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    @Autowired
    public void setMapManager(GameMapManager mapManager) {
        this.mapManager = mapManager;
    }

    private ConcurrentHashMap<Integer, CopyOnWriteArrayList<User>> userInMap;

    public GameMapServiceImpl() {

    }

    @Override
    public void broadcastSwitchMap(User operaUser, int toMapId) {

    }

    @Override
    public void moveLocation(User operaUser, int characterId, Location toLocation) {
        UserCharacter character = characterManager.getCharacterByCharacterId(characterId);
        if (character == null){
            throw new GlobalException("该ID号的角色不存在！");
        }
        character.setLocation(toLocation);
    }

}
