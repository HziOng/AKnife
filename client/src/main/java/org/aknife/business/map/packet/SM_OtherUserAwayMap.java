package org.aknife.business.map.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;

import java.util.List;

/**
 * 当其他角色离开地图，发送该协议给地图中其他角色
 * @ClassName CM_OtherUserSwitchMap
 * @Author HeZiLong
 * @Data 2021/1/21 16:56
 */
@Data
public class SM_OtherUserAwayMap extends Packet {

    /**
     * 要离开的用户ID
     */
    private int userId;

    /**
     * 要离开的用户的用户名
     */
    private String username;
}
