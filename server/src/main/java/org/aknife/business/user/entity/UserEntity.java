package org.aknife.business.user.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.aknife.business.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName UserEntity
 * @Author HeZiLong
 * @Data 2021/1/14 15:41
 */
@Entity
@Table(name = "t_user")
@Data
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name", length = 50)
    private String userName;

    @Column(name = "user_data", length = 255)
    private String data;

    private User user;

    @Column(name = "create_time", columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    private Date createTime;

    @Column(name = "update_time", columnDefinition="DATE")
    @Temporal(TemporalType.DATE)
    private Date updateTime;
}
