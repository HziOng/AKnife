package org.aknife.business.user.service;

import org.aknife.business.base.exception.*;
import org.aknife.business.user.model.User;

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
     * @return 返回执行状态
     * @throws GlobalException
     */
    int register(User operaUser) throws GlobalException;


    /**
     * 用户登录-协议请求操作
     * @param operaUser 要执行该操作的用户
     * @return 返回执行状态
     * @throws GlobalException
     */
    int login(User operaUser) throws GlobalException;


    /**
     * 用户退出-协议请求操作
     * @param operaUser 要执行该操作的用户
     * @return 返回响应号
     * @throws GlobalException
     */
    int dropOut(User operaUser) throws GlobalException;
}
