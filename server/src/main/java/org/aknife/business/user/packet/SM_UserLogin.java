package org.aknife.business.user.packet;

import lombok.Data;
import org.aknife.business.base.packet.Packet;
import org.aknife.resource.model.Location;

import java.util.HashMap;

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
     * 用户角色所在地图
     */
    private int mapID;

    /**
     * 用户角色所在位置
     */
    private HashMap<Integer, Location> locations = new HashMap<>();

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

    public SM_UserLogin(String username, int status, String news) {
        this.username = username;
        this.status = status;
        this.news = news;
    }

    public SM_UserLogin(int id, String username, int mapID,int status, String news) {
        this.id = id;
        this.username = username;
        this.mapID = mapID;
        this.status = status;
        this.news = news;
    }
}