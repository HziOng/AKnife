package org.aknife.business.map.manager;

import org.aknife.business.map.annotation.InjectResource;
import org.aknife.business.map.entity.GameMapResource;
import org.aknife.business.map.model.GameMap;
import org.aknife.business.map.util.GameMapUtil;

import org.aknife.business.user.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName GameMapManagerImpl
 * @Author HeZiLong
 * @Data 2021/1/20 15:36
 */
@Repository
public class GameMapManagerImpl implements GameMapManager {

    private final static String MAP_RESOURCE_PATH = "xlsx/map.xlsx";

    @InjectResource(path = MAP_RESOURCE_PATH)
    private HashMap<Integer, GameMapResource> mapResources = null;

    /**
     * 每个地图中的用户
     */
    private HashMap<Integer, HashSet<User>> userInMap = new HashMap<>();

    @PostConstruct
    public void initMethod() {
        Set<Integer> mapIds = getAllGameMapId();
        for (Integer id : mapIds) {
            userInMap.put(id, new HashSet<>());
        }
    }

    @Override
    public GameMap getGameMapById(int mapId) {
        GameMapResource resource = mapResources.get(mapId);
        if (resource == null) {
            return null;
        }
        return GameMapUtil.resourceToGameMap(resource);
    }

    @Override
    public Set<Integer> getAllGameMapId() {
        return mapResources.keySet();
    }

    @Override
    public HashSet<User> getUserInMap(int mapId) {
        return userInMap.get(mapId);
    }
}
