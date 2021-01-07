package org.hzl.aknife.hzlpNetty.api.pojo;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Date;

/**
 * 响应对象
 * @ClassName Response
 * @Author HeZiLong
 * @Data 2021/1/7 16:58
 */
public class Response {

    /**
     * 响应类型
     */
    private int model;

    /**
     * 状态码
     */
    private int status;

    /**
     * 响应时间
     */
    private Date date;

    /**
     * 响应数据
     */
    private JSONArray data;

    public Response(){}

    public Response(int model, int status, Date date, JSONArray data) {
        this.model = model;
        this.status = status;
        this.date = date;
        this.data = data;
    }

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

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "model=" + model +
                ", status=" + status +
                ", date=" + date +
                ", data=" + data +
                '}';
    }
}
