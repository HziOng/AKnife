package org.aknife.resource.model;

import lombok.Data;
import org.aknife.business.map.model.NpcCharacter;

import java.util.HashMap;

/**
 * 地图资源的JavaBean
 * @ClassName MapResource
 * @Author HeZiLong
 * @Data 2021/1/18 10:29
 */
@Data
public class MapResource {

    /**
     * 地图id
     */
    private int id;

    /**
     * 地图名字
     */
    private String name;

    private String url;

    /**
     * 该地图中的NPC角色对象
     */
    private HashMap<Integer, NpcCharacter> npc;

    public MapResource() {
        npc = new HashMap<>(0);
    }

}
