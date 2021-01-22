package org.aknife.business.character.service;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.model.Location;
import org.aknife.business.user.model.User;

import java.util.HashMap;

/**
 * @ClassName UserCharacterService
 * @Author HeZiLong
 * @Data 2021/1/19 9:59
 */
public interface IUserCharacterService {

    /**
     * 获取用户使用的默认角色
     * @param operaUser
     * @return
     */
    UserCharacter getInitCharacter(User operaUser);

    /**
     * 获取用户所有角色的位置
     * @param operaUser
     * @return
     */
    HashMap<Integer, Location> getLocationFromUser(User operaUser);
}
