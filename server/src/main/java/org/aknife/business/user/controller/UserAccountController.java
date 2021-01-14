package org.aknife.business.user.controller;

import lombok.extern.java.Log;
import org.aknife.business.base.controller.BaseController;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.user.model.User;
import org.aknife.business.user.packet.CM_UserLogin;
import org.aknife.business.user.packet.CM_UserOffLine;
import org.aknife.business.user.packet.CM_UserRegister;
import org.aknife.business.user.service.IUserAccountService;
import org.aknife.util.annotation.Operating;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int login(User operaUser, CM_UserLogin data) throws GlobalException {
        log.info("用户[ "+operaUser+" ]运行Controller登录方法");
        try {
            operaUser.setUsername(data.getUsername());
            operaUser.setPassword(data.getPassword());
            userAccountService.login(operaUser);
        } catch (GlobalException e){

        }
        return ProtocolFixedData.STATUS_OK;
    }

    @Operating
    public int register(User operaUser, CM_UserRegister data) throws GlobalException {
        log.info("用户[ "+operaUser+" ]运行Controller注册方法");
        operaUser.setUsername(data.getUsername());
        operaUser.setPassword(data.getPassword());
        try {
            userAccountService.register(operaUser);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        return ProtocolFixedData.STATUS_OK;
    }

    @Operating
    public int offLine(User operaUser, CM_UserOffLine data) throws GlobalException{
        log.info("用户[ "+operaUser+" ]运行Controller下线方法");
        return ProtocolFixedData.STATUS_OK;
    }

}
