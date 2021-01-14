package org.aknife.dao.mysql.user.impl;

import org.aknife.dao.mysql.user.UserAccountDao;
import org.aknife.business.user.model.User;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @ClassName UserAccountDaoImpl
 * @Author HeZiLong
 * @Data 2021/1/12 15:16
 */
@Repository
public class UserAccountDaoImpl implements UserAccountDao {


    private HibernateTemplate hibernateTemplate;

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void add(User user) {
        hibernateTemplate.save(user);
    }

    @Override
    public void delete(User user) {
        hibernateTemplate.delete(user);
    }

    @Override
    public User find(int id) {
        return hibernateTemplate.get(User.class, id);
    }

    @Override
    public User findByUserName(String username){
        System.out.println(hibernateTemplate.find("from UserEntity user where user.userName = ? ", username));
        return null;
    }

    @Override
    public void update(User user) {
        hibernateTemplate.update(user);
    }
}
