package org.aknife.business.user.model;

import lombok.Data;
import org.aknife.business.character.model.CharacterVo;

import java.util.List;

/**
 * @ClassName UserVO
 * @Author HeZiLong
 * @Data 2021/1/25 11:23
 */
@Data
public class UserVO {

    private Integer userID;

    private String username;

    private List<Integer> characterIds;
}
