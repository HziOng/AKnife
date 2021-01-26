package org.aknife.dao.mysql.user;

import lombok.extern.log4j.Log4j;
import org.aknife.business.user.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户账号访问数据库组件
 * @ClassName UserAccountDaoImpl
 * @Author HeZiLong
 * @Data 2021/1/12 15:16
 */

@Repository
@Log4j
public class UserAccountDaoImpl extends IUserAccountDao {

    @Override
    public UserEntity findByUserName(String username){
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UserEntity user where user.userName = ? ");
        query.setString(0, username);
        List<UserEntity> list = (List<UserEntity>)query.list();
        if (list == null || list.size() == 0){
            return null;
        }
        return list.get(0);
    }
}

