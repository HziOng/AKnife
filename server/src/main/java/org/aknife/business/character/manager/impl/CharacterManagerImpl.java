package org.aknife.business.character.manager.impl;

import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.entity.UserCharacterEntity;
import org.aknife.business.character.manager.CharacterManager;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.user.entity.UserEntity;
import org.aknife.business.user.model.User;
import org.aknife.cache.CacheManager;
import org.aknife.dao.mysql.user.IUserCharacterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户角色操作
 * @ClassName CharacterManagerImpl
 * @Author HeZiLong
 * @Data 2021/1/19 12:31
 */
@Repository
public class CharacterManagerImpl implements CharacterManager {

    private CacheManager cacheManager;

    private IUserCharacterDao userCharacterDao;

    @Autowired
    public void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Autowired
    public void setUserCharacterDao(IUserCharacterDao userCharacterDao) {
        this.userCharacterDao = userCharacterDao;
    }

    @Override
    public UserCharacter getCharacter(int userId, int heroId) {
        return null;
    }

    @Override
    public ConcurrentHashMap<Integer, UserCharacter> getCharacterByUserId(int userId) {
        ConcurrentHashMap<Integer, UserCharacter> cache = cacheManager.getCache(UserCharacterEntity.class);
        UserEntity userEntity = cacheManager.getClassObject(UserEntity.class, userId);
        ArrayList<Integer> characterIds = userEntity.getUser().getCharacterIds();
        ConcurrentHashMap<Integer, UserCharacter> result = new ConcurrentHashMap<>(5);
        for (Integer id : characterIds){
            result.put(id, cache.get(id));
        }
        return result;
    }

    @Override
    public ConcurrentHashMap<Integer, UserCharacter> getCharacterByUserId(User user) {
        ConcurrentHashMap<Integer, UserCharacterEntity> cache = cacheManager.getCache(UserCharacterEntity.class);
        ArrayList<Integer> characterIds = user.getCharacterIds();
        ConcurrentHashMap<Integer, UserCharacter> result = new ConcurrentHashMap<>(5);
        for (Integer id : characterIds){
            result.put(id, cache.get(id).getCharacter());
        }
        return result;
    }

    @Override
    public UserCharacter getCharacterByCharacterId(int characterId) {
        UserCharacterEntity characterEntity = cacheManager.getClassObject(UserCharacterEntity.class, characterId);
        if (characterEntity == null){
            return null;
        }
        return characterEntity.getCharacter();
    }

    @Override
    public void updateCharacter(UserCharacter character) {
        UserCharacterEntity entity = cacheManager.getClassObject(UserCharacterEntity.class, character.getId());
        if (entity == null){
            throw new GlobalException("该角色系统中不存在，请先插入该数据");
        }
        cacheManager.updateCache(entity);
    }

    @Override
    public void addCharacter(UserCharacter character) {
        UserCharacterEntity entity = cacheManager.getClassObject(UserCharacterEntity.class, character.getId());
        if (entity != null){
            throw new GlobalException("该角色已存在，请重新选择数据");
        }
        entity = new UserCharacterEntity();
        entity.setCharacter(character);
        cacheManager.addCacheIfAbsent(UserCharacterEntity.class, entity.getUserId() ,entity);
    }
}
