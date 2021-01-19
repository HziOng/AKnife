package org.aknife.business.user.character.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;

/**
 * 用户角色地图切换请求协议
 * @ClassName CM_SwitchMap
 * @Author HeZiLong
 * @Data 2021/1/18 17:12
 */
@Data
public class CM_SwitchMap extends Packet {

    /**
     * 用户所处的地图ID
     */
    private int mapID;

    /**
     * 用户要去的地图的ID
     */
    private int toMapID;
}
