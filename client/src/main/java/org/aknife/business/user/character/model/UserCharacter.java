package org.aknife.business.user.character.model;

import lombok.Data;
import org.aknife.business.user.model.User;
import org.aknife.resource.model.Location;

/**
 * @ClassName UserCharacter
 * @Author HeZiLong
 * @Data 2021/1/19 10:02
 */
@Data
public class UserCharacter {
    private String userName;

    private int mapID;

    private String mapName;

    private Location location;

    private User user;
}
