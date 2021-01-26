package org.aknife.dao.mysql;

import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

/**
 * 这里会定义hibernate中连接数据库的增删查改模板
 * @ClassName HibernateRealizationDao
 * @Author HeZiLong
 * @Data 2021/1/19 15:53
 */
@Log4j
public abstract class HibernateRealizationDao<T> implements BaseMySqlDao{

    protected SessionFactory sessionFactory;

    @Resource
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 向数据库中添加T
     * @param t 要添加的对象数据
     */
    public void add(T t){
        log.debug("add "+ t + " instance");
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
            session.close();
            log.debug("adding successful");
        } catch (RuntimeException e){
            log.error("adding failed", e);
            throw  e;
        }
    }

    /**
     * 删除数据库中符合条件T中属性的数据
     * @param t 要珊瑚的对象属性
     */
    public void delete(T t){
        log.debug("delete "+ t + " instance");
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
            session.close();
            log.debug("delete successful");
        } catch (RuntimeException e){
            log.error("delete failed", e);
            throw  e;
        }
    }

    /**
     * 根据用户id查询用户
     * @param id
     * @return 返回指定id的T对象
     */
    public T find(Class clazz, int id){
        log.debug("find "+ id + " instance");
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            T result = (T) session.get(clazz, id);
            session.getTransaction().commit();
            session.close();
            log.debug("find successful");
            return result;
        } catch (RuntimeException e){
            log.error("find failed", e);
            throw  e;
        }
    }

    /**
     * 修改该数据对象
     * @param t
     */
    public void update(T t){
        log.debug("update "+ t + " instance");
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
            session.close();
            log.debug("update successful");
        } catch (RuntimeException e){
            log.error("update failed", e);
            throw  e;
        }
    }

}
