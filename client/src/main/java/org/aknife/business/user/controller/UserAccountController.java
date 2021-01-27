package org.aknife.business.user.controller;

import lombok.extern.log4j.Log4j;
import org.aknife.business.base.controller.BaseController;
import org.aknife.business.character.packet.CM_SwitchMap;
import org.aknife.business.character.service.UserCharacterService;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.business.user.model.User;
import org.aknife.business.user.packet.SM_UserLogin;
import org.aknife.business.user.packet.SM_UserRegister;
import org.aknife.business.user.service.UserAccountService;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.connection.annotation.Operating;
import org.aknife.message.transmitter.PacketTransmitterUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserAccountService userAccountService;

    private UserCharacterService userCharacterService;

    private IGameMapService gameMapService;

    @Autowired
    public void setGameMapService(IGameMapService gameMapService) {
        this.gameMapService = gameMapService;
    }

    @Autowired
    public void setUserCharacterService(UserCharacterService userCharacterService) {
        this.userCharacterService = userCharacterService;
    }

    @Autowired
    public void setUserAccountService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    /**
     * 对客户端用户的登录请求的响应
     * @param response 来自服务端的响应数据
     */
    @Operating
    public int login(SM_UserLogin response){
        if (response.getStatus() == ProtocolFixedData.STATUS_OK) {
            User now = new User(response.getId(), response.getUsername(), response.getCharacterId(), response.getCharacterIds());
            userAccountService.updateUser(now, response.getMapID(), response.getCharacterId());
            userAccountService.closeLoginSwing();
            userCharacterService.toCharacterInfo(userAccountService.getInitCharacter());
            PacketTransmitterUtil.writePacket(new CM_SwitchMap(0,1));
        }else {
            userAccountService.loginFailure(response.getUsername(), response.getNews());
        }
        return ProtocolFixedData.STATUS_OK;
    }

    /**
     * 对客户端用户的登录请求的响应
     * @param response 来自服务端的响应数据
     */
    @Operating
    public int register(SM_UserRegister response){
        if (response.getStatus() == ProtocolFixedData.STATUS_OK) {
            userAccountService.closeRegisterSwing();
            userAccountService.toLoginSwing(response.getUsername());
        }else {
            userAccountService.registerFailure(response.getUsername(),response.getData());
        }
        return ProtocolFixedData.STATUS_OK;
    }
}
