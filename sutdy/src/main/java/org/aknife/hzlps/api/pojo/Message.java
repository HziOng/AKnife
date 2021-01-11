package org.aknife.hzlps.api.pojo;

import java.util.Date;

/**
 * @ClassName Message
 * @Author HeZiLong
 * @Data 2021/1/8 12:59
 */
public class Message {

    /**
     * 表示消息类型
     */
    private int model;

    /**
     * 表示消息状态
     */
    private int status;

    /**
     * 表示消息发送时间
     */
    private Date date;

    /**
     * 表示该消息中消息中体数据的长度
     */
    private int size;

    /**
     * 消息主体数据
     */
    private Object data;


    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
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
                "model=" + model +
                ", status=" + status +
                ", date=" + date +
                ", size=" + size +
                ", data=" + data.toString() +
                '}';
    }
}
