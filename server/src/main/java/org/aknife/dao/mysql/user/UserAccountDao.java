package org.aknife.dao.mysql.user;

import org.aknife.dao.mysql.BaseMySqlDao;
import org.aknife.business.user.model.User;

/**
 * @ClassName UserAccountDao
 * @Author HeZiLong
 * @Data 2021/1/12 14:57
 */
public interface UserAccountDao extends BaseMySqlDao<User> {


    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    User findByUserName(String username);

}
