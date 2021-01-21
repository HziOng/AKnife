package org.aknife.dao.mysql.user;

import lombok.extern.log4j.Log4j;
import org.aknife.business.user.entity.UserEntity;
import org.aknife.dao.mysql.user.IUserAccountDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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


    private HibernateTemplate hibernateTemplate;

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public UserEntity findByUserName(String username){
        List<UserEntity> list = (List<UserEntity>) hibernateTemplate.find("from UserEntity user where user.userName = ? ", username);
        if (list == null || list.size() == 0){
            return null;
        }
        return list.get(0);
    }
}

