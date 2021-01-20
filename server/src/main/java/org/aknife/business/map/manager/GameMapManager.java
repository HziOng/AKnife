package org.aknife.business.map.manager;

import org.aknife.business.map.model.GameMap;

/**
 * @ClassName GameMapManager
 * @Author HeZiLong
 * @Data 2021/1/20 15:35
 */
public interface GameMapManager {

    /**
     * 根据地图的mapId获取地图信息
     * @param mapId
     * @return
     */
    GameMap getGameMapById(int mapId);

}
