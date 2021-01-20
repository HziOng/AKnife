package org.aknife.dao.mysql.cache.impl;

import lombok.extern.log4j.Log4j;
import org.aknife.cache.CacheDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @ClassName MySqlCacheDaoImpl
 * @Author HeZiLong
 * @Data 2021/1/14 17:12
 */
@Repository
@Log4j
public class CacheDaoImpl implements CacheDao {

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
    public <K extends Serializable, V> V load(Class clazz, K key) {
        log.debug("load "+ key + " instance");
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            V value = (V) session.get(clazz,key);
            session.getTransaction().commit();
            session.close();
            log.debug("adding successful");
            return value;
        } catch (RuntimeException e){
            log.error("adding failed", e);
            throw  e;
        }
    }

    @Override
    public <V> void update(V value) {
        log.debug("update "+ value + " instance");
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(value);
            session.getTransaction().commit();
            session.close();
            log.debug("update successful");
        } catch (RuntimeException e){
            log.error("update failed", e);
            throw  e;
        }
    }

    @Override
    public void updateList(Object[] values) {
        log.debug("update "+values+" instance");
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object value : values){
                System.out.println(value);
                session.update(value);
            }
            session.getTransaction().commit();
            session.close();
            log.debug("update successful");
        } catch (RuntimeException e){
            log.error("update failed", e);
            throw  e;
        }
    }

    @Override
    public void addList(Object[] values) {
        log.debug("add "+values+" instance");
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object value : values){
                System.out.println(value);
                session.save(value);
            }
            session.getTransaction().commit();
            session.close();
            log.debug("add successful");
        } catch (RuntimeException e){
            log.error("add failed", e);
            throw  e;
        }
    }

    @Override
    public <K, V> void save(V value) {
        log.debug("save "+ value +" instance");
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(value);
            session.getTransaction().commit();
            session.close();
            log.debug("saving successful");
        } catch (RuntimeException e){
            log.error("saving failed", e);
            throw  e;
        }
    }
}
