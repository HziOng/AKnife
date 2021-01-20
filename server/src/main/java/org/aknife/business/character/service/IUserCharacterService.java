package org.aknife.business.character.service;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.user.model.User;

/**
 * @ClassName UserCharacterService
 * @Author HeZiLong
 * @Data 2021/1/19 9:59
 */
public interface IUserCharacterService {

    /**
     * 让user的所有角色前往地图toMapId
     * @param operaUser 要执行切换地图的角色
     * @param toMapId 要去的地图
     */
    void switchMapAllCharacter(User operaUser, int toMapId);

    /**
     * 指定角色前往指定地图
     * @param character
     * @param toMapId
     */
    void switchMap(UserCharacter character, int toMapId);

    /**
     * 获取用户使用的默认角色
     * @param operaUser
     * @return
     */
    UserCharacter getInitCharacter(User operaUser);
}
