package org.aknife.business.user.util;

import org.aknife.business.user.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于操作User对象的辅助工具类
 * @ClassName UserUtil
 * @Author HeZiLong
 * @Data 2021/1/12 12:31
 */
public class UserUtil {

    private static int number = 1;

    private static int now = 0;

    /**
     * 将一个User中的属性赋值到另一个User中
     * @param fromUser 数据来源User
     * @param toUser 数据目标User
     */
    public static void userCopyToUser(User fromUser, User toUser){
        toUser.setUserID(fromUser.getUserID());
        toUser.setMapId(fromUser.getMapId());
        toUser.setUsername(fromUser.getUsername());
        toUser.setPassword(fromUser.getPassword());
        toUser.setCharacterId(fromUser.getCharacterId());
        toUser.setCharacterIds(fromUser.getCharacterIds());
    }

    /**
     * 通过当前时间给用户创建一个用户ID
     * @return
     */
    public static int getUUID(){
        DateFormat format = new SimpleDateFormat("yyMMddss");
        String formatDate = format.format(new Date());
        Integer nowTime = Integer.parseInt(formatDate);
        if (nowTime != now){
            now = nowTime;
            number = 1;
        }
        return Integer.parseInt(""+formatDate+number++);
    }

    /**
     * 通过用户ID和英雄ID为角色生成一个唯一ID
     * @param userId
     * @param heroId
     * @return
     */
    public static int getCharacterId(int userId, int heroId){
        return userId*3+heroId;
    }
}
