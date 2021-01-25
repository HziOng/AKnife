package org.aknife.business.user.service;

import org.aknife.business.base.service.BaseService;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.map.model.Location;
import org.aknife.business.user.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @ClassName UserAccountService
 * @Author HeZiLong
 * @Data 2021/1/18 18:30
 */
@Service
public interface UserAccountService extends BaseService {

    /**
     * 更新用户信息
     * @param user
     * @param mapID
     * @param characterId
     */
    void updateUser(User user, int mapID, int characterId);

    /**
     * 用户登录失败后的客户端操作
     * @param username
     * @param message
     */
    void loginFailure(String username, String message);

    /**
     * 前往登录界面
     * @param username
     */
    void toLoginSwing(String username);

    /**
     * 关闭用户登录界面
     */
    void closeLoginSwing();

    /**
     * 注册失败后的数据显示处理
     * @param username
     * @param data
     */
    void registerFailure(String username, String data);

    /**
     * 关闭注册界面
     */
    void closeRegisterSwing();

    /**
     * 获取用户的默认角色
     * @return
     */
    UserCharacter getInitCharacter();

    /**
     * 获取客户端自生User对象
     * @return
     */
    User getMyUser();
}
