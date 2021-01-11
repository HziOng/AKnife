package org.aknife.user.service;

import org.aknife.common.PacketFixedData;
import org.aknife.user.packet.CM_UserLogin;
import org.aknife.util.annotation.Operating;

/**
 * 用户账号操作业务规则
 *
 * @ClassName UserAccountService
 * @Author HeZiLong
 * @Data 2021/1/11 15:04
 */
public interface IUserAccountService {

    /**
     * 用户注册-协议请求操作
     * @param data 请求数据，这里是用户账号
     * @return 返回执行状态
     */
    int register(CM_UserLogin data);


    /**
     * 用户登录-协议请求操作
     * @param data 请求数据，这里也是用户账号
     * @return 返回执行状态
     */
    int login(CM_UserLogin data);
}
