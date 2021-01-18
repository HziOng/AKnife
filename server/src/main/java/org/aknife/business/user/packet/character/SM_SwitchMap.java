package org.aknife.business.user.packet.character;

import org.aknife.business.user.packet.Packet;

/**
 * 用户地图切换响应
 * @ClassName CM_SwitchMap
 * @Author HeZiLong
 * @Data 2021/1/18 17:00
 */
public class SM_SwitchMap extends Packet {

    /**
     * 响应结果信息
     */
    private String message;

    public SM_SwitchMap() {
    }

    public SM_SwitchMap(String message) {
        this.message = message;
    }
}
