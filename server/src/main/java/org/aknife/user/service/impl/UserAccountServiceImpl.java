package org.aknife.user.service.impl;

import lombok.extern.java.Log;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.dao.mysql.user.UserAccountDao;
import org.aknife.user.constant.UserStatusConsts;
import org.aknife.user.exception.UserException;
import org.aknife.user.model.User;
import org.aknife.user.packet.CM_UserLogin;
import org.aknife.user.packet.CM_UserRegister;
import org.aknife.user.service.IUserAccountService;
import org.aknife.user.util.UserUtil;
import org.aknife.util.ProtocolFixedData;
import org.aknife.util.annotation.Operating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    @Operating
        public int register(User operaUser, CM_UserRegister data) throws UserException {
        log.info("用户[ "+operaUser+" ]运行注册方法");
        User user = userAccountDao.findByUserName(data.getUsername());
        if (user != null){
            throw new UserException("该用户已经存在");
        }
        operaUser.setUserID(UserUtil.getUUID());
        operaUser.setUserName(data.getUsername());
        operaUser.setPassword(data.getPassword());
        operaUser.setStatus(UserStatusConsts.OFF_LINE);
        userAccountDao.add(operaUser);
        return ProtocolFixedData.STATUS_OK;
    }

    @Override
    @Operating
    public int login(User operaUser, CM_UserLogin data) throws UserException {
        log.info("用户[ "+operaUser+" ]运行登录方法");
        User user = userAccountDao.findByUserName(data.getUsername());
        if (user == null){
            throw new UserException("用户不存在");
        }
        if (user.getStatus() == UserStatusConsts.ON_LINE) {
            throw new UserException("用户已在其他地方登录，确定强制登录");
        }
        UserUtil.userCopyToUser(user,operaUser);
        operaUser.setStatus(UserStatusConsts.ON_LINE);
        userAccountDao.update(operaUser);
        return ProtocolFixedData.STATUS_OK;
    }

    @Override
    public int dropOut(User operaUser, CM_UserLogin data) throws UserException {
        log.info("用户[ "+operaUser+" ]运行退出方法");
        return ProtocolFixedData.STATUS_OK;
    }

}
