package org.aknife.business.user.character.controller;

import lombok.extern.log4j.Log4j;
import org.aknife.business.base.controller.BaseController;
import org.aknife.business.user.character.service.IUserCharacterService;
import org.aknife.business.user.model.User;
import org.aknife.business.user.character.packet.CM_SwitchMap;
import org.aknife.business.user.character.packet.SM_SwitchMap;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.util.annotation.Operating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 用户角色操作业务
 * @ClassName UserCharacterController
 * @Author HeZiLong
 * @Data 2021/1/18 16:54
 */
@Log4j
@Controller
public class UserCharacterController extends BaseController {

    private IUserCharacterService userCharacterService;

    @Autowired
    public void setUserCharacterService(IUserCharacterService userCharacterService) {
        this.userCharacterService = userCharacterService;
    }

    /**
     * 用户角色切换地图协议处理
     * @param operaUser
     * @param request
     * @return
     */
    @Operating
    public int switchMap(User operaUser, CM_SwitchMap request){

        SM_SwitchMap response = new SM_SwitchMap(ProtocolFixedData.STATUS_OK, "switch map successful");
        writePacket(operaUser, response);
        return ProtocolFixedData.STATUS_OK;
    }
}
