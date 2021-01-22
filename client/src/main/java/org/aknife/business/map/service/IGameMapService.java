package org.aknife.business.map.service;

import org.aknife.business.base.service.BaseService;
import org.aknife.business.user.model.User;

import java.util.List;

/**
 * 地图业务主要实现
 * @ClassName MapService
 * @Author HeZiLong
 * @Data 2021/1/20 15:12
 */
public interface IGameMapService extends BaseService {

    /**
     * 将用户user的所有角色都送到toMapID地图去
     * @param toMapID
     */
    void switchMapAllCharacterFromUser(int toMapID);

    /**
     * 切换地图失败的信息回显
     * @param message
     */
    void switchMapFailure(String message);


    /**
     * 其他用户离开本地图
     * @param userId
     */
    void otherUserGoAwayMap(int userId);

    /**
     * 其他用户进入本地图
     * @param other
     */
    void otherUserEntryMap(User other);
}