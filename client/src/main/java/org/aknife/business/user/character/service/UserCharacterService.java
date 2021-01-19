package org.aknife.business.user.character.service;

import org.aknife.business.base.service.BaseService;
import org.aknife.business.user.character.model.UserCharacter;
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
    int toCharacterInfo(UserCharacter character);
}
