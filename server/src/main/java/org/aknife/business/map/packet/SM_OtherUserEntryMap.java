package org.aknife.business.map.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;

import java.util.List;

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

    /**
     * 进入地图用户的用户名
     */
    private String username;

    /**
     * 进入地图用户所带的角色
     */
    private List<Integer> characterIds;

    public SM_OtherUserEntryMap(int userId, String username, List<Integer> characterIds) {
        this.userId = userId;
        this.username = username;
        this.characterIds = characterIds;
    }
}
