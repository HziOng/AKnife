package org.aknife.business.map.service;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.manager.CharacterManager;
import org.aknife.business.character.model.CharacterVo;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.manager.GameMapManager;
import org.aknife.business.map.manager.GameMapManagerImpl;
import org.aknife.business.map.model.GameMap;
import org.aknife.business.map.model.Location;
import org.aknife.business.map.packet.SM_OtherMoveLocation;
import org.aknife.business.map.packet.SM_OtherUserAwayMap;
import org.aknife.business.map.packet.SM_OtherUserEntryMap;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.business.user.manager.UserManager;
import org.aknife.business.user.model.User;
import org.aknife.business.user.model.UserVO;
import org.aknife.connection.thread.CommonOperationThreadUtil;
import org.aknife.message.transmitter.PacketTransmitterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * 服务端游戏地图业务处理
 * @ClassName MapServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/20 15:13
 */
@Service
@Log
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
    private HashMap<Integer, HashSet<User>> userInMap = new HashMap<>();


    @PostConstruct
    public void initMethod() {
        Set<Integer> mapIds = mapManager.getAllGameMapId();
        for (Integer id : mapIds) {
            userInMap.put(id, new HashSet<>());
        }
    }

    @Override
    public void moveLocation(User operaUser, int characterId, Location toLocation) {
        UserCharacter character = characterManager.getCharacterByCharacterId(characterId);
        if (character == null){
            throw new GlobalException("该ID号的角色不存在！");
        }
        characterManager.getCharacterByCharacterId(characterId).setLocation(toLocation);
        character.setLocation(toLocation);
    }

    @Override
    public void notifyAllUserOfMap(int mapID, int toMapID, User operaUser) {
        if (userInMap.get(toMapID) != null){
            userInMap.get(toMapID).add(operaUser);
        }
        if (userInMap.get(mapID) != null){
            userInMap.get(mapID).remove(operaUser);
        }

        // 通知要去的地图中所有用户和本用户将要抵达该地图
        Runnable entryTask = new Runnable() {
            @Override
            public void run() {
                log.info("Thread:"+Thread.currentThread().getName());
                Set<User> users = userInMap.get(toMapID);
                for (User user : users){
                    SM_OtherUserEntryMap response = new SM_OtherUserEntryMap(operaUser.getUserID(),operaUser.getUsername(),operaUser.getCharacterIds());
                    PacketTransmitterUtil.writePacket(user, response);
                }
            }
        };

        // 向来源地图中的所有用户通知他们本用户离开了该地图
        Runnable awayTask = new Runnable(){
            @Override
            public void run() {
                log.info("Thread:"+Thread.currentThread().getName());
                if (mapID != 0){
                    Set<User> users = userInMap.get(mapID);
                    for (User user : users){
                        SM_OtherUserAwayMap response = new SM_OtherUserAwayMap(operaUser.getUserID());
                        PacketTransmitterUtil.writePacket(user, response);
                    }
                }
                CommonOperationThreadUtil.runTask(toMapID, entryTask);
            }
        };
        CommonOperationThreadUtil.runTask(mapID, awayTask);

    }

    @Override
    public void userSwitchMap(User operaUser, int toMapID) {
        operaUser.setMapId(toMapID);
    }

    @Override
    public List<UserVO> getUserVoInMap(int mapID) {
        Set<User> users = userInMap.get(mapID);
        List<UserVO> userVOS = users.stream().map(result -> new UserVO(result.getUserID(),result.getUsername(),
                        // 这里是每个用户的所有角色的包装类集合
                        characterManager.getCharacterByUserId(result.getUserID()).values().stream().map(now -> now.getId())
                            .collect(Collectors.toList())))
                .collect(Collectors.toList());
        return userVOS;
    }

    @Override
    public void notifyAllUserOfLocation(User operaUser, int characterId, Location fromLocation, Location toLocation) {
        // 告诉该地图中所有人该角色移动了
        Runnable task = new Runnable(){
            @Override
            public void run() {
                log.info("Thread:"+Thread.currentThread().getName());
                Set<User> users = userInMap.get(operaUser.getMapId());
                for (User user : users){
                    SM_OtherMoveLocation response = new SM_OtherMoveLocation(characterId, toLocation);
                    PacketTransmitterUtil.writePacket(user, response);
                }
            }
        };
        CommonOperationThreadUtil.runTask(operaUser.getMapId(), task);
    }
}
