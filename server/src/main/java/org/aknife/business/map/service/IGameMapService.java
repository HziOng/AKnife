package org.aknife.business.map.service;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.model.Location;
import org.aknife.business.user.model.User;

/**
 * 地图业务主要实现
 * @ClassName MapService
 * @Author HeZiLong
 * @Data 2021/1/20 15:12
 */
public interface IGameMapService {

    /**
     * 向toMapId的所有角色通知operaUser的到来
     * @param operaUser 要到达toMapId的角色
     * @param toMapId 要去的地图ID
     */
    void broadcastSwitchMap(User operaUser, int toMapId);


    /**
     * 指定用户的指定角色前往指定位置
     *
     * @param operaUser
     * @param characterId
     * @param toLocation
     * @return
     */
    void moveLocation(User operaUser, int characterId, Location toLocation);
}
