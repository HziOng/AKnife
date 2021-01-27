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

    private int id;

    private int heroId;

    private int userId;

    private String data;

    private Date createTime;

    private Date updateTime;

    private UserCharacter character;

    @Id
    public int getId() {
        return id;
    }

    @Column(name = "hero_id", length = 15)
    public int getHeroId() {
        return heroId;
    }

    @Column(name = "user_id", length = 15)
    public int getUserId() {
        return userId;
    }

    @Column(name = "data", length = 255)
    public String getData() {
        return data;
    }

    @Column(name = "create_time", columnDefinition="DATE")
    public Date getCreateTime() {
        return createTime;
    }

    @Column(name = "update_time", columnDefinition="DATE")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setCharacter(UserCharacter character) {
        this.id = character.getId();
        this.userId = character.getUserId();
        data = JSON.toJSONString(character);
        this.character = character;
    }

    @Transient
    public UserCharacter getCharacter() {
        if (character == null){
            character = JSON.parseObject(data, UserCharacter.class);
        }
        return character;
    }
}
