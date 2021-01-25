package org.aknife.resource.util;

import com.alibaba.fastjson.JSONObject;
import org.aknife.business.map.model.Location;

import java.util.HashMap;

/**
 * Json格式转换工具
 * @ClassName JSONUtils
 * @Author HeZiLong
 * @Data 2021/1/18 15:01
 */
public class JsonUtil {

    /**
     * 该方法将ncp的独白map转化为Json格式的字符串
     * @return
     */
    public static String mapToJson(){
        HashMap<Integer, String> monologue = new HashMap<>(10);
//        monologue.put(0,"冒险者，想变得和我一样强大吗，快充钱！");
//        monologue.put(1,"你可以去问问铁匠");
//        monologue.put(2,"你可以去问问酒店老板");
        return JSONObject.toJSONString(monologue);
    }

    public static String locationToJson(){
        Location location = new Location(50,50,50);
        return JSONObject.toJSONString(location);
    }


    public static void main(String[] args) {
    }
}
