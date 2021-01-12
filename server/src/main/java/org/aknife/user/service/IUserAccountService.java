package org.aknife.user.service;

import org.aknife.user.exception.UserException;
import org.aknife.user.model.User;
import org.aknife.user.packet.CM_UserLogin;
import org.aknife.user.packet.CM_UserRegister;

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
     * @param operaUser 要执行该操作的用户
     * @param data 请求数据，这里是用户账号
     * @return 返回执行状态
     * @throws UserException
     */
    int register(User operaUser, CM_UserRegister data) throws UserException;


    /**
     * 用户登录-协议请求操作
     * @param operaUser 要执行该操作的用户
     * @param data 请求数据，这里也是用户账号
     * @return 返回执行状态
     * @throws UserException
     */
    int login(User operaUser,CM_UserLogin data) throws UserException;


    /**
     * 用户退出-协议请求操作
     * @param operaUser 要执行该操作的用户
     * @param data 请求数据，这里也是用户账号
     * @return 返回响应号
     * @throws UserException
     */
    int dropOut(User operaUser,CM_UserLogin data) throws UserException;
}
