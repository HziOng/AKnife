package org.aknife.business.map.controller;

import org.aknife.business.base.controller.BaseController;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.packet.CM_SwitchMap;
import org.aknife.business.character.packet.SM_SwitchMap;
import org.aknife.business.character.service.IUserCharacterService;
import org.aknife.business.map.packet.CM_MoveLocation;
import org.aknife.business.map.packet.SM_MoveLocation;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.business.user.model.User;
import org.aknife.connection.annotation.Operating;
import org.aknife.constant.ProtocolFixedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 角色位置控制
 * @ClassName CharacterLocationController
 * @Author HeZiLong
 * @Data 2021/1/20 18:24
 */
@Controller
public class CharacterMapLocationController extends BaseController {

    private IUserCharacterService characterService;

    private IGameMapService gameMapService;

    @Autowired
    public void setGameMapService(IGameMapService gameMapService) {
        this.gameMapService = gameMapService;
    }

    @Autowired
    public void setCharacterService(IUserCharacterService characterService) {
        this.characterService = characterService;
    }

    @Operating
    public void movePlace(User operaUser, CM_MoveLocation request){
        try {
            gameMapService.moveLocation(operaUser,request.getCharacterId(), request.getToLocation());
        } catch (GlobalException e){
            e.printStackTrace();
        }
        // 这里可能要对地图中的其他用户发送用户移动广播
        SM_MoveLocation response = new SM_MoveLocation(ProtocolFixedData.STATUS_OK, "move successful");
        writePacket(operaUser, response);
    }

    /**
     * 用户角色切换地图协议处理
     * @param operaUser
     * @param request
     * @return
     */
    @Operating
    public int switchMap(User operaUser, CM_SwitchMap request){
        try {
            gameMapService.userSwitchMap(operaUser, request.getToMapID());
            gameMapService.notifyAllUserOfMap(request.getMapID(), request.getToMapID(), operaUser);
            SM_SwitchMap response = new SM_SwitchMap(ProtocolFixedData.STATUS_OK, request.getToMapID(), "switch map successful");
            writePacket(operaUser, response);
        } catch (GlobalException e){
            e.printStackTrace();
        }
        return ProtocolFixedData.STATUS_OK;
    }
}
