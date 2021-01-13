package org.aknife.user.service.impl;

import lombok.extern.java.Log;
import org.aknife.dao.mysql.user.UserAccountDao;
import org.aknife.user.constant.UserStatusConsts;
import org.aknife.user.exception.UserException;
import org.aknife.user.model.User;
import org.aknife.user.packet.CM_UserLogin;
import org.aknife.user.packet.CM_UserRegister;
import org.aknife.user.service.IUserAccountService;
import org.aknife.user.util.UserUtil;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.util.annotation.Operating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户账号操作业务
 * @ClassName UserAccountServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/11 15:17
 */
@Log
@Service
public class UserAccountServiceImpl implements IUserAccountService {

    private UserAccountDao userAccountDao;

    @Autowired
    public void setUserAccountDao(UserAccountDao userAccountDao) {
        this.userAccountDao = userAccountDao;
    }

    @Override
    public int register(User operaUser) throws UserException {
        log.info("用户[ "+operaUser+" ]运行注册方法");
        if (operaUser == null){
            throw new UserException("输入用户数据为空");
        }
        User now = userAccountDao.findByUserName(operaUser.getUserName());
        if (now != null){
            throw new UserException("该用户名已被注册");
        }
        return ProtocolFixedData.STATUS_OK;
    }

    @Override
    public int login(User operaUser) throws UserException {
        log.info("用户[ "+operaUser+" ]运行登录方法");
        if (operaUser == null){
            throw new UserException("输入用户数据为空");
        }
        User now  = userAccountDao.findByUserName(operaUser.getUserName());
        if (now == null){
            throw new UserException("该用户不存在，请注册");
        }
        if (now.getStatus() == UserStatusConsts.ON_LINE){
            throw new UserException("该用户已经登录，确定强制登录");
        }
        now.setStatus(UserStatusConsts.ON_LINE);
        userAccountDao.update(now);
        return ProtocolFixedData.STATUS_OK;
    }

    @Override
    public int dropOut(User operaUser) throws UserException {
        log.info("用户[ "+operaUser+" ]运行退出方法");
        return ProtocolFixedData.STATUS_OK;
    }

}
