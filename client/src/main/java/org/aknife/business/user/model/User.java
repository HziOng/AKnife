package org.aknife.business.user.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
     * 默认使用的角色
     */
    private int characterId;

    /**
     * 用户状态：上线还是下线
     */
    private int status;

    private List<Integer> characterIds = new ArrayList<>();

    public User() {
    }

    public User(int userID, String username, int characterId, List<Integer> characterIds) {
        this.userID = userID;
        this.username = username;
        this.characterId = characterId;
        this.characterIds = characterIds;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
