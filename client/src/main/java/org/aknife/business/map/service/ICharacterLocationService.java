package org.aknife.business.map.service;

import org.aknife.business.base.service.BaseService;
import org.aknife.business.map.model.Location;

/**
 * @ClassName CharacterLocationService
 * @Author HeZiLong
 * @Data 2021/1/21 10:16
 */
public interface ICharacterLocationService extends BaseService {

    /**
     * 本用户移动成功，进行显示
     */
    void moveLocation();


    /**
     * 移动位置失败
     * @param message
     */
    void movePlaceFailure(String message);

    /**
     * characterId角色移动到toLocation位置
     * @param characterId
     * @param toLocation
     */
    void otherMoveLocation(int characterId, Location toLocation);
}
