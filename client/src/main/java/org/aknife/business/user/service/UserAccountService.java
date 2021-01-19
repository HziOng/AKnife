package org.aknife.business.user.service;

import org.aknife.business.base.service.BaseService;
import org.aknife.business.user.model.User;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserAccountService
 * @Author HeZiLong
 * @Data 2021/1/18 18:30
 */
@Service
public interface UserAccountService extends BaseService {

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
}
