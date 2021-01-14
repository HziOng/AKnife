package org.aknife.business.user.manager;

import org.aknife.business.user.model.User;

/**
 * @ClassName UserManager
 * @Author HeZiLong
 * @Data 2021/1/14 15:48
 */
public interface UserManager {

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
}
