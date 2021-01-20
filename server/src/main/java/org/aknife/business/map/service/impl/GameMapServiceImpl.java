package org.aknife.business.map.service.impl;

import org.aknife.business.map.manager.GameMapManager;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.business.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    public GameMapServiceImpl() {

    }

    @Override
    public void broadcastSwitchMap(User operaUser, int toMapId) {

    }
}
