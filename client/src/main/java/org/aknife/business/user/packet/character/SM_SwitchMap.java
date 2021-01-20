package org.aknife.business.user.packet.character;


import lombok.Data;
import org.aknife.business.user.packet.Packet;

/**
 * 用户地图切换响应
 * @ClassName CM_SwitchMap
 * @Author HeZiLong
 * @Data 2021/1/18 17:00
 */
@Data
public class SM_SwitchMap extends Packet {

    /**
     * 表示响应是成功还是失败，1表示成功，2表示失败
     */
    private int status;

    /**
     * 要去的地图
     */
    private int toMapId;

    /**
     * 响应结果信息
     */
    private String message;
}
