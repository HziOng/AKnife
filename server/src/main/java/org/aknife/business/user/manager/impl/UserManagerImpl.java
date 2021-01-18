package org.aknife.business.user.manager.impl;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.user.manager.UserManager;
import org.aknife.business.user.model.User;
import org.aknife.business.user.entity.UserEntity;
import org.aknife.cache.CacheManager;
import org.aknife.dao.mysql.user.UserAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户缓存管理
 * @ClassName UserManagerImpl
 * @Author HeZiLong
 * @Data 2021/1/14 15:49
 */
@Repository
public class UserManagerImpl implements UserManager {

    private CacheManager manager;

    private UserAccountDao userAccountDao;

    @Autowired
    public void setManager(CacheManager manager) {
        this.manager = manager;
    }

    @Autowired
    public void setUserAccountDao(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    public void updateUser(User user) {
        UserEntity userEntity = manager.getClassObject(UserEntity.class, user.getUserID());
        if (userEntity == null){
            throw new GlobalException("系统中不存在"+user.getUserID()+"的用户");
        }
        userEntity.setUser(user);
        manager.updateCache(userEntity);
    }

    @Override
    public User queryUser(int id) {
        UserEntity userEntity = manager.getClassObject(UserEntity.class, id);
        if (userEntity == null){
            return null;
        }
        return userEntity.getUser();
    }

    @Override
    public User queryUserByName(String username) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(210115561);
/*        UserEntity userEntity = userAccountDao.findByUserName(username);
        if (userEntity == null){
            return null;
        }*/
        UserEntity now = manager.getClassObject(UserEntity.class, userEntity.getId());
        System.out.println(now);
        manager.addCacheIfAbsent(UserEntity.class,userEntity.getId(),userEntity);
        return userEntity.getUser();
    }

    @Override
    public void addUser(User user){
        if (user == null){
            return;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUser(user);
        userEntity.setCreateTime(new Date());
        userEntity.setUpdateTime(new Date());
        manager.addCacheIfAbsent(UserEntity.class, user.getUserID(), userEntity);
        userAccountDao.add(userEntity);
        manager.addCacheIfAbsent(UserEntity.class, userEntity.getId(), userEntity);
    }
}
