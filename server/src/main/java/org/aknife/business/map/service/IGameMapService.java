package org.aknife.business.map.service;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.model.Location;
import org.aknife.business.user.model.User;
import org.aknife.business.user.model.UserVO;

import java.util.List;

/**
 * 地图业务主要实现
 * @ClassName MapService
 * @Author HeZiLong
 * @Data 2021/1/20 15:12
 */
public interface IGameMapService {

    /**
     * 指定用户的指定角色前往指定位置
     *
     * @param operaUser
     * @param characterId
     * @param toLocation
     * @return
     */
    void moveLocation(User operaUser, int characterId, Location toLocation);


    /**
     * 当用户离开或进入地图时候通知对应地图的用户
     * @param mapID
     * @param toMapID
     * @param operaUser
     */
    void notifyAllUserOfMap(int mapID, int toMapID, User operaUser);


    /**
     * 用户切换地图
     * @param operaUser
     * @param toMapID
     */
    void userSwitchMap(User operaUser, int toMapID);

    /**
     * 获取指定地图中的存在的用户信息，并封装为客户端显示形态
     * @param mapID
     * @return
     */
    List<UserVO> getUserVoInMap(int mapID);
}
