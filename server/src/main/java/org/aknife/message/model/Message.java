package org.aknife.message.model;

import io.netty.channel.Channel;
import lombok.Data;
import org.aknife.business.user.model.User;

import java.util.Date;

/**
 * 客户端和服务端之间沟通的消息，类似于Http协议中的request和Response
 * @ClassName Message
 * @Author HeZiLong
 * @Data 2021/1/11 9:56
 */
@Data
public class Message<T> {

    /**
     * 消息类型
     */
    private int type;

    /**
     * 消息发送时间
     */
    private Date date;

    /**
     * 消息主体数据大小（以byte为单位）
     */
    private int size;

    /**
     * 消息主体数据
     */
    private T data;

    public Message(){}

    public Message(int type, Date date, T data) {
        this.type = type;
        this.date = date;
        this.data = data;
    }


}
