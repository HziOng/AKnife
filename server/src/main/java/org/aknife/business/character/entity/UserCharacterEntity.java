package org.aknife.business.character.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.aknife.business.character.model.UserCharacter;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户角色实体对象，对应数据库
 * @ClassName CharacterEntity
 * @Author HeZiLong
 * @Data 2021/1/19 12:20
 */
@Data
@Entity()
@Table(name = "t_character")
@Lazy(value = false)
public class UserCharacterEntity {

    @Id
    private int id;

    @Column(name = "hero_id", length = 15)
    private int heroId;

    @Column(name = "user_id", length = 15)
    private int userId;

    @Column(name = "data", length = 255)
    private String data;

    @Column(name = "create_time", columnDefinition="DATE")
    private Date createTime;

    @Column(name = "update_time", columnDefinition="DATE")
    private Date updateTime;

    @Transient
    private UserCharacter character;

    public void setCharacter(UserCharacter character) {
        this.id = character.getId();
        this.userId = character.getUserId();
        data = JSON.toJSONString(character);
        this.character = character;
    }

    public UserCharacter getCharacter() {
        if (character == null){
            character = JSON.parseObject(data, UserCharacter.class);
        }
        return character;
    }
}
