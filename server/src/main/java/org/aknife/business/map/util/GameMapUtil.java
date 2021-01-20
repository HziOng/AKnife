package org.aknife.business.map.util;

import org.aknife.business.map.entity.GameMapEntity;
import org.aknife.business.map.model.GameMap;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @ClassName GameMapUtil
 * @Author HeZiLong
 * @Data 2021/1/20 16:29
 */
public class GameMapUtil {

    /**
     * 将数据库实体对象转化为model对象
     * @param entity
     * @return
     */
    public static GameMap entityToGameMap(GameMapEntity entity){
        GameMap map = new GameMap();
        map.setId(entity.getId());
        map.setName(entity.getName());
        map.setNumberLimit(entity.getNumberLimit());
        map.setNpcId(Arrays.asList(entity.getNpcIds().split(",")).stream().mapToInt(Integer::parseInt).toArray());
        return map;
    }
}
