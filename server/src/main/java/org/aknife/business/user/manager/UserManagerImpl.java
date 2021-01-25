package org.aknife.business.user.manager;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.user.manager.UserManager;
import org.aknife.business.user.model.User;
import org.aknife.business.user.entity.UserEntity;
import org.aknife.cache.CacheManager;
import org.aknife.dao.mysql.user.IUserAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 用户缓存管理
 * @ClassName UserManagerImpl
 * @Author HeZiLong
 * @Data 2021/1/14 15:49
 */
@Repository
public class UserManagerImpl implements UserManager {

    private CacheManager cacheManager;

    private IUserAccountDao userAccountDao;

    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Autowired
    public void setUserAccountDao(IUserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    public void login(User user) {
        UserEntity entity = new UserEntity();
        entity.setUser(user);
        userAccountDao.update(entity);
        cacheManager.addCacheIfAbsentNoUpdateToMySQL(UserEntity.class, entity.getId(), entity);
    }

    @Override
    public void updateUser(User user) {
        UserEntity userEntity = cacheManager.getClassObject(UserEntity.class, user.getUserID());
        if (userEntity == null){
            throw new GlobalException("系统中不存在"+user.getUserID()+"的用户");
        }
        userEntity.setUser(user);
        cacheManager.updateCache(userEntity);
    }



    @Override
    public User queryUser(int id) {
        UserEntity userEntity = cacheManager.getClassObject(UserEntity.class, id);
        if (userEntity == null){
            return null;
        }
        return userEntity.getUser();
    }

    @Override
    public User queryUserByName(String username) {
        UserEntity userEntity = userAccountDao.findByUserName(username);
        if (userEntity == null){
            return null;
        }
        cacheManager.addCacheIfAbsentNoUpdateToMySQL(UserEntity.class,userEntity.getId(),userEntity);
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
        userAccountDao.add(userEntity);
        cacheManager.addCacheIfAbsentNoUpdateToMySQL(UserEntity.class, userEntity.getId(), userEntity);
    }
}
