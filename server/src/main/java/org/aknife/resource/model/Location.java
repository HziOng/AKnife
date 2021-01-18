package org.aknife.resource.model;

import lombok.Data;

/**
 * 角色在地图中的位置
 * @ClassName Location
 * @Author HeZiLong
 * @Data 2021/1/18 14:37
 */
@Data
public class Location {

    /**
     * 以下三个变量组合成在一个地图中的坐标
     */
    private int x;

    private int y;

    private int z;

    public Location() {
    }

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        } else if (obj == null || obj.getClass() != getClass()){
            return false;
        }
        Location now = (Location) obj;
        if (x == now.x && y == now.y && z == now.z){
            return true;
        }
        return false;
    }
}
