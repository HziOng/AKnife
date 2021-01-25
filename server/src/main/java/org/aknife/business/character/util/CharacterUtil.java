package org.aknife.business.character.util;

import org.aknife.business.character.entity.UserCharacterEntity;
import org.aknife.business.character.model.UserCharacter;

/**
 * @ClassName CharacterUtil
 * @Author HeZiLong
 * @Data 2021/1/25 12:20
 */
public class CharacterUtil {

    public static UserCharacter entityToModel(UserCharacterEntity entity){
        return entity.getCharacter();
    }
}
