package org.aknife.user.packet;

/**
 * @ClassName UserLoginPacket
 * @Author HeZiLong
 * @Data 2021/1/11 16:43
 */
public class CM_UserLogin {

    private String username;

    private String password;

    public CM_UserLogin(){};

    public CM_UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        return "CM_UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
