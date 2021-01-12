package org.aknife.user.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Author HeZiLong
 * @Data 2021/1/11 11:34
 */
@Entity
@Table(name = "t_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", length = 11)
    private Integer userID;

    @Column(name = "user_name",length = 11)
    private String userName;

    @Column(name = "password",length = 11)
    private String password;

    @Column(name = "status", length = 4)
    private int status;

    @Column(name = "create_time", columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    private Date createTime;

    @Column(name = "update_time", columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    private Date updateTime;


    public User(){}

    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public User(Integer userID, String username, String password) {
        this.userID = userID;
        this.userName = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
