package org.aknife.business.user.controller;

import lombok.extern.java.Log;
import org.aknife.business.base.controller.BaseController;
import org.aknife.business.user.packet.account.*;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.user.model.User;
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

    /**
     * 处理用户登录请求
     * @param operaUser
     * @param data
     * @return
     */
    @Operating
    public int login(User operaUser, CM_UserLogin data) {
        log.info("用户[ "+operaUser+" ]运行Controller登录方法");
        try {
            int interimID = operaUser.getUserID();
            operaUser.setUsername(data.getUsername());
            operaUser.setPassword(data.getPassword());
            userAccountService.login(operaUser);
            updatePacketTransmitter(interimID, operaUser.getUserID());
            SM_UserLogin response = new SM_UserLogin(operaUser.getUserID(),operaUser.getUsername(),operaUser.getPassword(), "login successful");
            writePacket(operaUser, response);
        } catch (GlobalException e){
            log.info(e.getMessage());
        }
        return ProtocolFixedData.STATUS_OK;
    }

    /**
     * 处理用户注册请求
     * @param operaUser
     * @param request
     * @return
     */
    @Operating
    public int register(User operaUser, CM_UserRegister request) {
        log.info("用户[ "+operaUser+" ]运行Controller注册方法");
        if (!request.getPassword().equals(request.getConfirmPassword())){
            return ProtocolFixedData.STATUS_FAILURE;
        }
        operaUser.setUsername(request.getUsername());
        operaUser.setPassword(request.getPassword());
        try {
            userAccountService.register(operaUser);
        } catch (GlobalException e) {
            e.printStackTrace();
        }
        SM_UserRegister response = new SM_UserRegister(operaUser.getUsername(),operaUser.getPassword(),"registration success");
        writePacket(operaUser, response);
        return ProtocolFixedData.STATUS_OK;
    }

    /**
     * 用户下线请求
     * @param operaUser
     * @param data
     * @return
     */
    @Operating
    public int offLine(User operaUser, CM_UserOffLine data){
        log.info("用户[ "+operaUser+" ]运行Controller下线方法");
        return ProtocolFixedData.STATUS_OK;
    }

}
