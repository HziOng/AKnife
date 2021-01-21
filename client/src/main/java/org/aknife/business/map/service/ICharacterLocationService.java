package org.aknife.business.map.service;

import org.aknife.business.base.service.BaseService;

/**
 * @ClassName CharacterLocationService
 * @Author HeZiLong
 * @Data 2021/1/21 10:16
 */
public interface ICharacterLocationService extends BaseService {

    /**
     *
     */
    void moveLocation();


    /**
     * 移动位置失败
     * @param message
     */
    void movePlaceFailure(String message);
}
