package org.aknife.business.user.character.model;

import lombok.Data;
import org.aknife.resource.model.Location;

/**
 * 用户角色
 * @ClassName UserCharacter
 * @Author HeZiLong
 * @Data 2021/1/19 9:54
 */
@Data
public class UserCharacter {

    private int id;

    private int mapID;

    private Location location;

}
