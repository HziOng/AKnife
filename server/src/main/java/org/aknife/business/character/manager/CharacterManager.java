package org.aknife.business.character.manager;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.user.model.User;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 用户角色操作业务接口
 * @ClassName CharacterManager
 * @Author HeZiLong
 * @Data 2021/1/19 12:30
 */
public interface CharacterManager {

    /**
     * 获取id为userId的用户的 heroId角色，如果该用户为拥有该角色，返回null
     * @param userId
     * @param heroId
     * @return
     */
    UserCharacter getCharacter(int userId, int heroId);

    /**
     * 返回指定id用户的所有hero角色
     * @param userId
     * @return
     */
    ConcurrentHashMap<Integer, UserCharacter> getCharacterByUserId(int userId);

    /**
     * 返回指定用户的所有角色
     * @param user
     * @return
     */
    ConcurrentHashMap<Integer, UserCharacter> getCharacterByUserId(User user);

    /**
     * 通过角色ID获取角色
     * @param characterId
     * @return
     */
    UserCharacter getCharacterByCharacterId(int characterId);

    /**
     * 更新指定用户数据
     * @param character
     */
    void updateCharacter(UserCharacter character);

    /**
     * 给用户新建角色
     * @param character
     */
    void addCharacter(UserCharacter character);

}
