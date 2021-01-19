package org.aknife.business.user.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;

/**
 * 注册协议
 * @ClassName SM_UserRegister
 * @Author HeZiLong
 * @Data 2021/1/15 15:27
 */
@Data
public class SM_UserRegister extends Packet {

    private String username;

    private int status;

    private String data;

    public SM_UserRegister(String username,String data) {
        super();
        this.username = username;
        this.data = data;
    }
}
