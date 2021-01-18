package org.aknife.business.user.controller;

import lombok.extern.log4j.Log4j;
import org.aknife.business.base.controller.BaseController;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.user.model.User;
import org.aknife.business.user.packet.character.CM_SwitchMap;
import org.aknife.business.user.packet.character.SM_SwitchMap;
import org.aknife.constant.ProtocolFixedData;

/**
 * 用户角色操作业务
 * @ClassName UserCharacterController
 * @Author HeZiLong
 * @Data 2021/1/18 16:54
 */
@Log4j
public class UserCharacterController extends BaseController {

    /**
     * 用户角色切换地图协议处理
     * @param operaUser
     * @param request
     * @return
     */
    public int switchMap(User operaUser, CM_SwitchMap request){
        SM_SwitchMap response = null;
        if (operaUser.getMapID() != request.getMapID()){
            String message = "用户地图异常，客户端所处位置与服务端不一致>> client："+ request.getMapID() +" >> server: "+operaUser.getMapID();
            log.info(message);
            response = new SM_SwitchMap(message);
        }else {
            operaUser.setMapID(request.getToMapID());
            log.info("用户从地图 " + request.getMapID() + " 去了地图 " + request.getToMapID());
            response = new SM_SwitchMap();
        }
        writePacket(operaUser, response);
        return ProtocolFixedData.STATUS_OK;
    }
}
