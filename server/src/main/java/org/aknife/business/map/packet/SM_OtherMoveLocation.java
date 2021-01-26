package org.aknife.business.map.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;
import org.aknife.business.map.model.Location;

/**
 * 当用户移动的时候，向本地图中的所有用户发送本协议，通知其他人
 * @ClassName SM_OtherMoveLocation
 * @Author HeZiLong
 * @Data 2021/1/26 12:50
 */
@Data
public class SM_OtherMoveLocation extends Packet {

    /**
     * 要移动的角色ID
     */
    private int characterId;

    /**
     * 要移动到的位置
     */
    private Location toLocation;

    public SM_OtherMoveLocation(int characterId, Location toLocation) {
        this.characterId = characterId;
        this.toLocation = toLocation;
    }
}
