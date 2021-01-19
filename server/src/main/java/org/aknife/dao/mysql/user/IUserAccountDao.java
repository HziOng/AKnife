package org.aknife.dao.mysql.user;

import org.aknife.business.user.entity.UserEntity;
import org.aknife.dao.mysql.BaseMySqlDao;
import org.aknife.business.user.model.User;
import org.aknife.dao.mysql.HibernateRealizationDao;

/**
 * 用户账号访问数据库组件
 * @ClassName UserAccountDao
 * @Author HeZiLong
 * @Data 2021/1/12 14:57
 */
public abstract class IUserAccountDao extends HibernateRealizationDao {


    /**
     * 通过用户名查询用户
     * @param username
     * @return
     */
    public abstract UserEntity findByUserName(String username);

}
