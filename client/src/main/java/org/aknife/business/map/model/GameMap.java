package org.aknife.business.map.model;

import lombok.Data;

/**
 * 游戏地图model
 * @ClassName GameMap
 * @Author HeZiLong
 * @Data 2021/1/20 15:21
 */
@Data
public class GameMap {

    /**
     * 地图id
     */
    private int id;

    /**
     * 地图名字
     */
    private String name;


    private String url;

}
