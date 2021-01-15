package org.aknife.dao.mysql.user.impl;

import lombok.extern.log4j.Log4j;
import org.aknife.business.user.entity.UserEntity;
import org.aknife.dao.mysql.user.UserAccountDao;
import org.aknife.business.user.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

/**
 * @ClassName UserAccountDaoImpl
 * @Author HeZiLong
 * @Data 2021/1/12 15:16
 */

@Repository
@Log4j
public class UserAccountDaoImpl implements UserAccountDao {


    private HibernateTemplate hibernateTemplate;

    private SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void add(UserEntity user) {
        log.debug("add "+user+ " instance");
        try {
            System.out.println(user);
/*            hibernateTemplate.setCheckWriteOperations(false);
            hibernateTemplate.save(user);
            hibernateTemplate.flush();*/
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            log.debug("adding successful");
        } catch (RuntimeException e){
            log.error("adding failed", e);
            throw  e;
        }
    }

    @Override
    public void delete(UserEntity user) {
        hibernateTemplate.delete(user);
    }

    @Override
    public UserEntity find(int id) {
        return hibernateTemplate.get(UserEntity.class, id);
    }

    @Override
    public UserEntity findByUserName(String username){
        List<UserEntity> list = (List<UserEntity>) hibernateTemplate.find("from UserEntity user where user.userName = ? ", username);
        if (list == null || list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public void update(UserEntity user) {
        hibernateTemplate.update(user);
    }
}

