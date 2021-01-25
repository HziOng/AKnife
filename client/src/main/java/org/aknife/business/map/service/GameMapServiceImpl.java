package org.aknife.business.map.service;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.manager.GameMapManager;
import org.aknife.business.map.model.Location;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.business.user.model.User;
import org.aknife.business.map.swing.SwingGameForm;
import org.aknife.business.user.model.UserVO;
import org.aknife.resource.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @ClassName MapServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/20 15:13
 */
@Service
public class GameMapServiceImpl implements IGameMapService {

    private GameMapManager mapManager;

    @Autowired
    public void setMapManager(GameMapManager mapManager) {
        this.mapManager = mapManager;
    }


    private SwingGameForm checkSwingForm(){
        JFrame jFrame = jFrameStack.peek();
        if (!gameForm.equals(jFrame.getTitle())){
            throw new GlobalException("该界面不是游戏界面，无法切换地图");
        }
        return (SwingGameForm) jFrame;
    }

    @Override
    public void switchMapAllCharacterFromUser(int toMapID, List<UserVO> userVOS) {
        SwingGameForm form = checkSwingForm();
        form.setMapText(ResourceManager.getMapNameByID(toMapID));
        form.setMapImage(ResourceManager.getMapImageUrlByID(toMapID));
        form.clearMessage();

        userVOS.forEach(userVO -> {
            otherUser.put(userVO.getUserID(),new User(userVO.getUserID(),userVO.getUsername(),userVO.getCharacterIds()));
        });

        form.setLocationText(characters.get(user.getCharacterId()).getLocation().toString());
        // 这里的character是用户的所有角色,mapCharacter表示该用户的所有角色
        HashMap<Integer, UserCharacter> mapCharacter = form.getCharacters();
        mapCharacter.clear();
        for (Integer id : characters.keySet()){
            mapCharacter.put(id, characters.get(id));
        }
        form.repaint();
    }

    @Override
    public void switchMapFailure(String message) {
        SwingGameForm form = checkSwingForm();
        form.setError(message);
        form.repaint();
    }

    @Override
    public void otherUserGoAwayMap(int userId) {
        SwingGameForm form = checkSwingForm();
        User other = otherUser.get(userId);
        if (other == null){
            throw new GlobalException(userId+"该用户不存在于本地图,，无法离开地图");
        }
        form.addMessage("用户【" + other.getUsername() + "】 离开地图 "+ form.getGameMapString());
        otherUser.remove(userId);
        form.repaint();
    }

    @Override
    public void otherUserEntryMap(User other) {
        SwingGameForm form = checkSwingForm();
        if (other == null){
            throw new GlobalException(other.getUsername()+"该用户不存在于本地图，无法进入地图");
        }
        form.addMessage("用户【" + other.getUsername() + "】 进入地图 "+ form.getGameMapString());
        otherUser.put(other.getUserID(), other);
        for (Integer id : other.getCharacterIds()){
            characters.put(id, new UserCharacter(id,other.getUsername(),new Location(10,10,0)));
        }
    }
}
