package org.aknife.business.map.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;

/**
 * 角色在用户的移动响应协议
 * @ClassName SM_MoveLocation
 * @Author HeZiLong
 * @Data 2021/1/21 9:57
 */
@Data
public class SM_MoveLocation extends Packet {

    private int status;

    private String message;

    public SM_MoveLocation(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
