package org.hzl.aknife.hzlpNetty.api.pojo;

import org.json.JSONArray;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName Request
 * @Author HeZiLong
 * @Data 2021/1/7 16:58
 */
public class Request {

    /**
     * 请求类型
     */
    private int model;

    /**
     * 请求时间
     */
    private Date date;

    /**
     * 请求数据
     */
    private JSONArray data;

    public Request(){}

    public Request(int model, Date date, JSONArray data) {
        this.model = model;
        this.date = date;
        this.data = data;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Request{" +
                "model=" + model +
                ", date=" + date +
                ", data=" + data +
                '}';
    }
}
