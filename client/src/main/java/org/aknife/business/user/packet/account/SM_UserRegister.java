package org.aknife.business.user.packet.account;

import lombok.Data;

/**
 * 注册协议
 * @ClassName SM_UserRegister
 * @Author HeZiLong
 * @Data 2021/1/15 15:27
 */
@Data
public class SM_UserRegister {

    private String username;

    private String password;

    private String data;
}
