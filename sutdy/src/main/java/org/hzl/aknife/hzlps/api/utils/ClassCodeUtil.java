package org.hzl.aknife.hzlps.api.utils;

import org.hzl.aknife.hzlps.api.pojo.Location;
import org.hzl.aknife.hzlps.api.pojo.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ClassCodeUtils
 * @Author HeZiLong
 * @Data 2021/1/8 15:00
 */
public class ClassCodeUtil {

    private static ConcurrentHashMap<Integer, Class> classCodeMap = new ConcurrentHashMap();

    static {
        classCodeMap.put(1, User.class);
        classCodeMap.put(2, Location.class);
    }

    public static Class getClassByCode(int code){
        return classCodeMap.get(code);
    }
}
