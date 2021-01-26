package org.aknife.business.character.model;

import lombok.Data;
import org.aknife.business.map.model.Location;

import java.util.Objects;

/**
 * 用户角色
 * @ClassName UserCharacter
 * @Author HeZiLong
 * @Data 2021/1/19 9:54
 */
@Data
public class UserCharacter {

    private int id;

    private int userId;

    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCharacter)) {
            return false;
        }
        UserCharacter character = (UserCharacter) o;
        return getId() == character.getId() && getUserId() == character.getUserId() && Objects.equals(getLocation(), character.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
