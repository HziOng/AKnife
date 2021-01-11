package org.aknife.message.model;

import java.util.Date;

/**
 * 客户端和服务端之间沟通的消息，类似于Http协议中的request和Response
 * @ClassName Message
 * @Author HeZiLong
 * @Data 2021/1/11 9:56
 */
public class Message {

    /**
     * 消息类型
     */
    private int type;

    /**
     * 消息状态
     */
    private int status;

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
    private Object data;

    public Message(){}

    public Message(int type, int status, Date date, Object data) {
        this.type = type;
        this.status = status;
        this.date = date;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", status=" + status +
                ", date=" + date +
                ", size=" + size +
                ", data=" + data +
                '}';
    }
}
