package org.aknife.business.map.manager;

import org.aknife.business.map.model.GameMap;
import org.aknife.business.user.model.User;

import java.util.List;
import java.util.Set;

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

    /**
     * 获取所有GameMap对象的id
     * @return
     */
    Set<Integer> getAllGameMapId();
}
