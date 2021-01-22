package org.aknife.business.map.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;

/**
 * 当其他角色进入地图，发送该协议给地图中其他角色
 * @ClassName CM_OtherUserSwitchMap
 * @Author HeZiLong
 * @Data 2021/1/21 16:56
 */
@Data
public class SM_OtherUserEntryMap extends Packet {

    /**
     * 要操作的用户ID
     */
    private int userId;

    public SM_OtherUserEntryMap(int userId) {
        this.userId = userId;
    }
}
