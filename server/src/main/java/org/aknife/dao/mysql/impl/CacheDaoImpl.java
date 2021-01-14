package org.aknife.dao.mysql.impl;

import org.aknife.business.user.model.User;
import org.aknife.dao.mysql.CacheDao;
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
public class CacheDaoImpl implements CacheDao {

    private HibernateTemplate hibernateTemplate;

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public <K extends Serializable, V> V load(Class clazz, K key) {
        return (V) hibernateTemplate.load(clazz,key);
    }

    @Override
    public <V> void update(V value) {
        hibernateTemplate.update(value);
    }

    @Override
    public void updateList(Object[] values) {
        for (Object o : values){
            hibernateTemplate.save(o);
        }
    }

    @Override
    public <K, V> void save(V value) {
        hibernateTemplate.save(value);
    }
}
