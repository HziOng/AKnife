package org.aknife.resource;

import org.aknife.business.map.entity.GameMapEntity;

import java.util.HashMap;

/**
 * @ClassName ResourceManager
 * @Author HeZiLong
 * @Data 2021/1/18 10:12
 */
public class ResourceManager {

    /**
     * 地形地图
     */
    private static HashMap<Integer, GameMapEntity> terrainMap = null;


    public static String getMapNameByID(int mapID){
        return terrainMap.get(mapID).getName();
    }

    public static String getMapImageUrlByID(int mapID){
        return terrainMap.get(mapID).getUrl();
    }

    public static void setTerrainMap(HashMap<Integer, GameMapEntity> terrainMap) {
        ResourceManager.terrainMap = terrainMap;
    }
}
