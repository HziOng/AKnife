package org.aknife.business.user.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.aknife.business.user.model.User;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserEntity
 * @Author HeZiLong
 * @Data 2021/1/14 15:41
 */
@Data
@Entity
@Table(name = "t_user")
@Lazy(value = false)
public class UserEntity implements Serializable {

    @Id
    private int id;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "user_data", length = 255)
    private String data;

    @Transient
    private User user;

    @Column(name = "create_time", columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    private Date createTime;

    @Column(name = "update_time", columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    private Date updateTime;

    public void setUserName(String userName) {
        this.userName = userName;
        getUser().setUsername(userName);
    }

    public void setUser(User user) {
        this.user = user;
        id = user.getUserID();
        data = JSON.toJSONString(user);
        userName = user.getUsername();
    }

    public User getUser() {
        if (user == null){
            user = JSON.parseObject(data, User.class);
        }
        return user;
    }
}
