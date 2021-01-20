package org.aknife.business.character.model;

import lombok.Data;
import org.aknife.resource.model.Location;

/**
 * @ClassName UserCharacter
 * @Author HeZiLong
 * @Data 2021/1/19 10:02
 */
@Data
public class UserCharacter {

    private int id;

    private String username;

    private int mapID;

    private Location location;

}
