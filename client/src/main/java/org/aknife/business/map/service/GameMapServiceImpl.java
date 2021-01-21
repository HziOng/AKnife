package org.aknife.business.map.service;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.manager.GameMapManager;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.business.user.model.User;
import org.aknife.business.map.swing.SwingGameForm;
import org.aknife.resource.ResourceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

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

    private ConcurrentHashMap<Integer, CopyOnWriteArrayList<User>> userInMap;

    private SwingGameForm checkSwingForm(){
        JFrame jFrame = jFrameStack.peek();
        if (!gameForm.equals(jFrame.getTitle())){
            throw new GlobalException("该界面不是游戏界面，无法切换地图");
        }
        return (SwingGameForm) jFrame;
    }

    @Override
    public void switchMapAllCharacterFromUser(int toMapID) {
        SwingGameForm form = checkSwingForm();
        form.setMapText(ResourceManager.getMapNameByID(toMapID));
        form.setMapImage(ResourceManager.getMapImageUrlByID(toMapID));
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
    public void userGoAwayMap(int userId, String username) {
        SwingGameForm form = checkSwingForm();
        form.addMessage("用户【" + username + "】 离开地图 "+ form.getGameMapString());
        List<Integer> characterIds = otherUser.get(userId).getCharacterIds();
        otherUser.remove(userId);

    }

    @Override
    public void userEntryMap(int userId, int username, List<Integer> characterId) {
        SwingGameForm form = checkSwingForm();
        form.addMessage("用户【" + username + "】 进入地图 "+ form.getGameMapString());
    }
}
