package org.aknife.user.util;

import javafx.scene.input.DataFormat;
import org.aknife.user.model.User;
import org.joda.time.DateTime;

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
        toUser.setUserName(fromUser.getUserName());
        toUser.setPassword(fromUser.getPassword());
    }

    /**
     * 通过当前时间给用户创建一个用户ID
     * @return
     */
    public static int getUUID(){
        int result = 0;
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String formatDate = format.format(new Date());
        Integer nowTime = Integer.parseInt(formatDate);
        if (nowTime != now){
            now = nowTime;
            number = 1;
        }
        return Integer.parseInt(""+formatDate+number++);
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
