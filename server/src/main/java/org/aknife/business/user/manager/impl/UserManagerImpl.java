package org.aknife.business.user.manager.impl;

import org.aknife.business.user.manager.UserManager;
import org.aknife.business.user.model.User;
import org.aknife.business.user.entity.UserEntity;
import org.aknife.cache.CacheManager;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户缓存管理
 * @ClassName UserManagerImpl
 * @Author HeZiLong
 * @Data 2021/1/14 15:49
 */
public class UserManagerImpl implements UserManager {

    CacheManager manager = CacheManager.getInstance();

    @Override
    public void updateUser(User user) {
        UserEntity userEntity = manager.getClassObject(UserEntity.class, user.getUserID());
        userEntity.setUser(user);
        manager.updateCache(userEntity);
    }

    @Override
    public User queryUser(int id) {
        UserEntity userEntity = manager.getClassObject(UserEntity.class, id);
        return userEntity.getUser();
    }

    @Override
    public User queryUserByName(String username) {
        ConcurrentHashMap<Integer, UserEntity> cacheUser = manager.getCache(UserEntity.class);
        if (cacheUser.containsValue(username)){

        }
        return null;

    }
}
