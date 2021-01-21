package org.aknife.business.user.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;

import java.util.List;

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

    /**
     * 用户默认使用的角色
     */
    private int characterId;

    /**
     * 用户角色所在地图
     */
    private int mapID;

    /**
     * 该用户的所有角色
     */
    private List<Integer> characterIds;

    /**
     * 表示请求状态
     */
    private int status;

    /**
     * 消息
     */
    private String news;

    public SM_UserLogin() {
    }

    public SM_UserLogin(int id, String username, int characterId, int mapID, int statu, List<Integer> characterIds, String news) {
        this.id = id;
        this.username = username;
        this.characterId  = characterId;
        this.mapID = mapID;
        this.status = status;
        this.news = news;
        this.characterIds = characterIds;
    }

    public SM_UserLogin(String username , int status, String news) {
        this.username = username;
        this.status = status;
        this.news = news;
    }
}
