package org.aknife.business.map.manager.impl;

import org.aknife.business.map.entity.GameMapEntity;
import org.aknife.business.map.manager.GameMapManager;
import org.aknife.business.map.model.GameMap;
import org.aknife.business.map.util.GameMapUtil;
import org.aknife.resource.util.ExcelUtil;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName GameMapManagerImpl
 * @Author HeZiLong
 * @Data 2021/1/20 15:36
 */
@Repository
public class GameMapManagerImpl implements GameMapManager {

    private final static String MAP_RESOURCE_PATH = "xlsx/map.xlsx";

    private HashMap<Integer, GameMapEntity> mapResources = null;

    public GameMapManagerImpl(){
        loadMapResource();
    }

    /**
     * 加载配置文件中的地图配置
     */
    public void loadMapResource(){
        mapResources = ExcelUtil.getBeanMappingID(MAP_RESOURCE_PATH, GameMapEntity.class);
    }

    @Override
    public GameMap getGameMapById(int mapId){
        GameMapEntity entity = mapResources.get(mapId);
        if (entity == null){
            return null;
        }
        return GameMapUtil.entityToGameMap(entity);
    }

    public static void main(String[] args) {
        GameMapManager mapManager = new GameMapManagerImpl();
        System.out.println(mapManager.getGameMapById(0));
    }
}
