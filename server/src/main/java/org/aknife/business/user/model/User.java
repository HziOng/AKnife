package org.aknife.business.user.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

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
     * 用户所在地图
     */
    private int mapId;

    /**
     * 默认使用的角色
     */
    private int characterId;

    /**
     * 用户状态：上线还是下线
     */
    private int status;

    private ArrayList<Integer> characterIds = new ArrayList<>();

    public User() {
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
