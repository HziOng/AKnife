package org.aknife.business.user.model;

import lombok.Data;

/**
 * @ClassName User
 * @Author HeZiLong
 * @Data 2021/1/11 11:34
 */
@Data
public class User {

    private Integer userID;

    private String username;

    private String password;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
