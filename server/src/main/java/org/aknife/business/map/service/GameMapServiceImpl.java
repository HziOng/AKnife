package org.aknife.business.map.service;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.manager.CharacterManager;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.manager.GameMapManager;
import org.aknife.business.map.model.GameMap;
import org.aknife.business.map.model.Location;
import org.aknife.business.map.packet.SM_OtherUserAwayMap;
import org.aknife.business.map.packet.SM_OtherUserEntryMap;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.business.user.manager.UserManager;
import org.aknife.business.user.model.User;
import org.aknife.connection.thread.CommonOperationThreadUtil;
import org.aknife.message.transmitter.PacketTransmitterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
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

    /**
     * 每个地图中的用户
     */
    private ConcurrentHashMap<Integer, CopyOnWriteArrayList<User>> userInMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void initMethod() {
        Set<Integer> mapIds = mapManager.getAllGameMapId();
        for (Integer id : mapIds) {
            userInMap.put(id, new CopyOnWriteArrayList<>());
        }
    }

    @Override
    public void moveLocation(User operaUser, int characterId, Location toLocation) {
        UserCharacter character = characterManager.getCharacterByCharacterId(characterId);
        if (character == null){
            throw new GlobalException("该ID号的角色不存在！");
        }
        character.setLocation(toLocation);
    }

    @Override
    public void notifyAllUserOfMap(int mapID, int toMapID, User operaUser) {

        // 向来源地图中的所有用户通知他们本用户离开了该地图
        Runnable task = new Runnable(){
            @Override
            public void run() {
                List<User> users = userInMap.get(mapID);
                for (User user : users){
                    SM_OtherUserAwayMap response = new SM_OtherUserAwayMap(operaUser.getUserID());
                    PacketTransmitterUtil.writePacket(user, response);
                }
            }
        };
        CommonOperationThreadUtil.runTask(mapID, task);

        // 通知要去的地图中所有用户和本用户将要抵达该地图
        task = new Runnable() {
            @Override
            public void run() {
                List<User> users = userInMap.get(toMapID);
                for (User user : users){
                    SM_OtherUserEntryMap response = new SM_OtherUserEntryMap(operaUser.getUserID());
                    PacketTransmitterUtil.writePacket(user, response);
                }
            }
        };
        CommonOperationThreadUtil.runTask(mapID, task);
    }

    @Override
    public void userSwitchMap(User operaUser, int toMapID) {
        operaUser.setMapId(toMapID);
    }

}
