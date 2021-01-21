package org.aknife.business.map.entity;

import lombok.Data;
import org.aknife.resource.annotation.ExcelCell;

/**
 * 游戏地图实体类，对应配置文件中数据
 * @ClassName MapEntity
 * @Author HeZiLong
 * @Data 2021/1/20 15:18
 */
@Data
public class GameMapEntity {

    /**
     * 地图id
     */
    @ExcelCell(col = 0, Type = Integer.class)
    private int id;

    /**
     * 地图名字
     */
    @ExcelCell(col = 1)
    private String name;


    /**
     * 该地图中的NPC角色对象
     */
    @ExcelCell(col = 2)
    private String url;


}
