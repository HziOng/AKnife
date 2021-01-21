package org.aknife.business.map.model;

import lombok.Data;
import org.aknife.resource.model.Location;

import java.util.HashMap;

/**
 * 地图中非控制玩家NPC的JavaBean
 * @ClassName NPCCharacter
 * @Author HeZiLong
 * @Data 2021/1/18 14:32
 */
@Data
public class NpcCharacter {

    /**
     * NPC的id号
     */
    private int id;

    /**
     * NPC名字
     */
    private String name;

    /**
     * 任务独白（表示点击NPC之后显示的语言）,这里使用map是由于当玩家进行不同的任务时候需要说不同的话
     */
    private HashMap<Integer, String> monologue;

    /**
     * 表示该NPC处于哪个地图中
     */
    private int mapId;

    /**
     * 该NPC在地图中的位置
     */
    private Location location;
}
