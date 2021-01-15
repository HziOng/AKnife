package org.aknife.business.user.packet;

/**
 * 注册协议
 * @ClassName UserLoginPacket
 * @Author HeZiLong
 * @Data 2021/1/11 16:43
 */
public class CM_UserRegister extends Packet {

    private String username;

    private String password;

    private String confirmPassword;

    public CM_UserRegister(){}

    public CM_UserRegister(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "CM_UserRegister{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
