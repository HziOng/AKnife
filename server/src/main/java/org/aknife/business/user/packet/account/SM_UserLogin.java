package org.aknife.business.user.packet.account;

import lombok.Data;
import org.aknife.business.user.packet.Packet;

/**
 * 用户登录响应
 * @ClassName SM_UserLogin
 * @Author HeZiLong
 * @Data 2021/1/15 14:32
 */
@Data
public class SM_UserLogin extends Packet {
    /**
     * 用户ID
     */
    private int id;

    private String username;

    private String password;

    /**
     * 消息
     */
    private String news;

    public SM_UserLogin() {
    }

    public SM_UserLogin(int id, String username, String password, String news) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.news = news;
    }
}
