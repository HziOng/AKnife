package org.aknife.business.map.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;
import org.aknife.business.map.model.Location;

/**
 * 角色在地图中移动协议
 * @ClassName CM_MoveLocation
 * @Author HeZiLong
 * @Data 2021/1/21 9:54
 */
@Data
public class CM_MoveLocation extends Packet {

    /**
     * 移动的角色
     */
    private int characterId;

    /**
     * 移动的初始位置
     */
    private Location fromLocation;

    /**
     * 要去的位置
     */
    private Location toLocation;
}
