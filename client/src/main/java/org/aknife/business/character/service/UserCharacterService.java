package org.aknife.business.character.service;

import org.aknife.business.base.service.BaseService;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.user.model.User;

/**
 * @ClassName UserCharacterService
 * @Author HeZiLong
 * @Data 2021/1/19 10:02
 */
public interface UserCharacterService extends BaseService {

    /**
     * 去角色信息界面
     * @param character
     * @return
     */
    void toCharacterInfo(UserCharacter character);

    /**
     * 将用户user的所有角色都送到toMapID地图去
     * @param user
     * @param toMapID
     */
    void switchMapAllCharacterFromUser(User user, int toMapID);

    /**
     * 切换地图失败的信息回显
     * @param message
     */
    void switchMapFailure(String message);
}
