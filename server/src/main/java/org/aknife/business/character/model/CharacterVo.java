package org.aknife.business.character.model;

import lombok.Data;
import org.aknife.business.map.model.Location;

/**
 * @ClassName CharacterVo
 * @Author HeZiLong
 * @Data 2021/1/25 11:25
 */
@Data
public class CharacterVo {

    private int id;

    private Location location;

    public CharacterVo(int id, Location location) {
        this.id = id;
        this.location = location;
    }
}
