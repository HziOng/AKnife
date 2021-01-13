package org.aknife.user.controller;

import lombok.extern.java.Log;
import org.aknife.base.controller.BaseController;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.message.model.Message;
import org.aknife.user.exception.UserException;
import org.aknife.user.model.User;
import org.aknife.user.packet.CM_UserLogin;
import org.aknife.user.packet.CM_UserOffLine;
import org.aknife.user.packet.CM_UserRegister;
import org.aknife.user.service.IUserAccountService;
import org.aknife.util.annotation.Operating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @ClassName UserAccountController
 * @Author HeZiLong
 * @Data 2021/1/13 10:59
 */
@Controller
@Log
public class UserAccountController extends BaseController {

    private IUserAccountService userAccountService;

    @Autowired
    public void setUserAccountService(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Operating
    public int login(User operaUser, CM_UserLogin data) throws UserException {
        log.info("用户[ "+operaUser+" ]运行Controller登录方法");
        try {
            userAccountService.login(operaUser);
        } catch (UserException e){

        }
        return ProtocolFixedData.STATUS_OK;
    }

    @Operating
    public int register(User operaUser, CM_UserRegister data) throws UserException {
        log.info("用户[ "+operaUser+" ]运行Controller注册方法");
        operaUser.setUserName(data.getUsername());
        operaUser.setPassword(data.getPassword());
        try {
            userAccountService.login(operaUser);
        } catch (UserException e){

        }
        return ProtocolFixedData.STATUS_OK;
    }

    @Operating
    public int offLine(User operaUser, CM_UserOffLine data) throws UserException{
        log.info("用户[ "+operaUser+" ]运行Controller下线方法");
        return ProtocolFixedData.STATUS_OK;
    }

}
