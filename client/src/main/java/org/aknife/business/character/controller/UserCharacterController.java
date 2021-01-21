package org.aknife.business.character.controller;

import org.aknife.business.base.controller.BaseController;
import org.aknife.business.character.service.UserCharacterService;
import org.aknife.business.character.packet.SM_SwitchMap;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.connection.annotation.Operating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassName UserCharacterController
 * @Author HeZiLong
 * @Data 2021/1/19 11:49
 */
@Controller
public class UserCharacterController extends BaseController {

    private UserCharacterService characterService;

    private IGameMapService gameMapService;

    @Autowired
    public void setGameMapService(IGameMapService gameMapService) {
        this.gameMapService = gameMapService;
    }

    @Autowired
    public void setCharacterService(UserCharacterService characterService) {
        this.characterService = characterService;
    }

    /**
     * 切换地图业务
     * @param response
     */
    @Operating
    public void switchMap(SM_SwitchMap response){
        if (response.getStatus() == ProtocolFixedData.STATUS_OK) {
            gameMapService.switchMapAllCharacterFromUser(response.getToMapId());
        }else {
            gameMapService.switchMapFailure(response.getMessage());
        }
    }

}
