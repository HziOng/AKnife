package org.aknife.business.user.packet;

import lombok.Data;

/**
 * 注册协议
 * @ClassName SM_UserRegister
 * @Author HeZiLong
 * @Data 2021/1/15 15:27
 */
@Data
public class SM_UserRegister extends Packet {

    private String username;

    private String password;

    private String data;

    public SM_UserRegister() {
    }

    public SM_UserRegister(String username, String password, String data) {
        this.username = username;
        this.password = password;
        this.data = data;
    }
}
