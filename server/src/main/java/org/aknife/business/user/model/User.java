package org.aknife.business.user.model;

import lombok.Data;
import org.aknife.resource.model.Location;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author HeZiLong
 * @Data 2021/1/11 11:34
 */
@Data
public class User implements Serializable {

    private Integer userID;

    private String username;

    private String password;

    /**
     * 用户状态：上线还是下线
     */
    private int status;

    /**
     * 用户所处的地图ID
     */
    private Integer mapID;

    /**
     * 用户所处的地图上的位置
     */
    private Location location;

    public User() {
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
