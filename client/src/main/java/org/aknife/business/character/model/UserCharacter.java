package org.aknife.business.character.model;

import lombok.Data;
import org.aknife.business.map.model.Location;

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

    public UserCharacter(int id, String username, int mapID, Location location) {
        this.id = id;
        this.username = username;
        this.mapID = mapID;
        this.location = location;
    }
}
