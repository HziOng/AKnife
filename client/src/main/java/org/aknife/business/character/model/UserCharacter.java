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

    private Location location;

    public UserCharacter(int id, String username, Location location) {
        this.id = id;
        this.username = username;
        this.location = location;
    }
}
