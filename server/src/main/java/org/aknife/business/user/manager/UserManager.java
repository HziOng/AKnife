package org.aknife.business.user.manager;

import org.aknife.business.user.model.User;

/**
 * @ClassName UserManager
 * @Author HeZiLong
 * @Data 2021/1/14 15:48
 */
public interface UserManager {

    /**
     * 由于注册方法时候缓存的特殊性，特意写了该方法
     * @param user
     */
    void login(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * 根据用户的id查询用户
     * @param id
     * @return
     */
    User queryUser(int id);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User queryUserByName(String username);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);
}
