package org.aknife.business.map.util;

import org.aknife.business.map.entity.GameMapResource;
import org.aknife.business.map.model.GameMap;

import java.util.Arrays;

/**
 * @ClassName GameMapUtil
 * @Author HeZiLong
 * @Data 2021/1/20 16:29
 */
public class GameMapUtil {

    /**
     * 将数据库实体对象转化为model对象
     * @param resource
     * @return
     */
    public static GameMap resourceToGameMap(GameMapResource resource){
        GameMap map = new GameMap();
        map.setId(resource.getId());
        map.setName(resource.getName());
        map.setNumberLimit(resource.getNumberLimit());
        map.setNpcId(Arrays.asList(resource.getNpcIds().split(",")).stream().mapToInt(Integer::parseInt).toArray());
        return map;
    }
}
