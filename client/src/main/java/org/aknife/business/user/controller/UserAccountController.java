package org.aknife.business.user.controller;

import lombok.extern.log4j.Log4j;
import org.aknife.business.base.controller.BaseController;
import org.aknife.business.user.model.User;
import org.aknife.business.user.packet.SM_UserLogin;
import org.aknife.business.user.packet.SM_UserRegister;
import org.aknife.constant.PacketFixedConsts;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.util.annotation.Operating;
import org.springframework.stereotype.Controller;

/**
 * 客户端响应请求
 * @ClassName UserAccountController
 * @Author HeZiLong
 * @Data 2021/1/15 15:47
 */
@Controller
@Log4j
public class UserAccountController extends BaseController {

    /**
     * 对客户端用户的登录请求的响应
     * @param response 来自服务端的响应数据
     */
    @Operating
    public int login(SM_UserLogin response){
        user = new User(response.getUsername(),response.getPassword());
        user.setUserID(response.getId());
        System.out.println(user+"::"+response.getNews());
        return ProtocolFixedData.STATUS_OK;
    }

    /**
     * 对客户端用户的登录请求的响应
     * @param response 来自服务端的响应数据
     */
    @Operating
    public int register(SM_UserRegister response){
        user = new User(response.getUsername(),response.getPassword());
        System.out.println(user+"::"+response.getData());
        return ProtocolFixedData.STATUS_OK;
    }
}
