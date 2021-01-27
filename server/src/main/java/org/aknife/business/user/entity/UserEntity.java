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

    private int id;

    private String userName;

    private String userData;

    private User user;

    private Date createTime;

    private Date updateTime;

    @Id
    public int getId() {
        return id;
    }

    @Column(name = "user_name", length = 50)
    public String getUserName() {
        return userName;
    }

    @Column(name = "user_data", length = 255)
    public String getUserData() {
        userData = JSON.toJSONString(user);
        return userData;
    }

    @Transient
    public User getUser() {
        if (user == null){
            user = JSON.parseObject(userData, User.class);
        }
        return user;
    }

    @Column(name = "create_time", columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    public Date getCreateTime() {
        return createTime;
    }


    @Column(name = "update_time", columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    public Date getUpdateTime() {
        return updateTime;
    }


    public void setUserData(String data) {
        user = JSON.parseObject(data, User.class);
        this.userData = data;
    }

    public void setUser(User user) {
        this.user = user;
        id = user.getUserID();
        userName = user.getUsername();
    }


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", user_data='" + userData + '\'' +
                ", user=" + user +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
