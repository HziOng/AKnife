package org.aknife.resource;

import org.aknife.resource.model.MapResource;

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
    private static HashMap<Integer, MapResource> terrainMap = null;

    /**
     * 用于加载配置资源
     */
    private static ResourceLoader loader = new ResourceLoader();

    static {
        // 加载地图配置资源
        terrainMap = ResourceLoader.loadMapInfo();
        // 加载NPC配置资源
        ResourceLoader.loadNpcInfo();
    }
}
