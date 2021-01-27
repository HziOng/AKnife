package org.aknife.business.map.manager;

import org.aknife.business.base.manager.BaseManager;
import org.aknife.business.map.model.GameMap;
import org.aknife.business.user.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName GameMapManager
 * @Author HeZiLong
 * @Data 2021/1/20 15:35
 */
public interface GameMapManager extends BaseManager {

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

    /**
     * 获取指定id的地图中所有用户
     * @param mapId
     * @return
     */
    HashSet<User> getUserInMap(int mapId);

}
