package org.aknife.dao.mysql.user.impl;

import org.aknife.dao.mysql.user.UserAccountDao;
import org.aknife.user.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName UserAccountDaoImpl
 * @Author HeZiLong
 * @Data 2021/1/12 15:16
 */
@Repository
public class UserAccountDaoImpl implements UserAccountDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(User user) {
        Session session = getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        Session session = getCurrentSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public User find(int id) {
        return getCurrentSession().get(User.class, id);
    }

    @Override
    public User findByUserName(String username){
        Session session = getCurrentSession();
        session.beginTransaction();
        String hql = "from User as user where user.userName = :username";
        Query query = session.createQuery(hql);
        query.setString("username", username);
        List<User> userList = query.list();
        session.getTransaction().commit();
        if (userList==null || userList.size() == 0){
            return null;
        }
        return userList.get(0);
    }

    @Override
    public void update(User user) {
        Session session = getCurrentSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
    }
}
