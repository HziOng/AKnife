package org.aknife.business.map.manager;

import org.aknife.business.map.annotation.InjectResource;
import org.aknife.business.map.entity.GameMapResource;
import org.aknife.business.map.model.GameMap;
import org.aknife.business.map.util.GameMapUtil;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
}
