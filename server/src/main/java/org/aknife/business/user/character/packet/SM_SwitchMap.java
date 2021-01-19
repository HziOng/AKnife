package org.aknife.business.user.character.packet;

import org.aknife.business.base.packet.Packet;

/**
 * 用户地图切换响应
 * @ClassName CM_SwitchMap
 * @Author HeZiLong
 * @Data 2021/1/18 17:00
 */
public class SM_SwitchMap extends Packet {

    /**
     * 表示响应是成功还是失败，1表示成功，2表示失败
     */
    private int status;

    /**
     * 响应结果信息
     */
    private String message;

    public SM_SwitchMap() {
    }

    public SM_SwitchMap(String message) {
        this.message = message;
    }

    public SM_SwitchMap(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
