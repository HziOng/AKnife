package org.aknife.business.user.packet;

import org.aknife.business.base.packet.Packet;

/**
 * 用户注册协议
 * @ClassName UserLoginPacket
 * @Author HeZiLong
 * @Data 2021/1/11 16:43
 */
public class CM_UserLogin extends Packet {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CM_UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
