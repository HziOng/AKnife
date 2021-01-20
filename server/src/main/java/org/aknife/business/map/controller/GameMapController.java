package org.aknife.business.map.controller;

import org.aknife.business.base.controller.BaseController;
import org.aknife.business.character.packet.CM_SwitchMap;
import org.aknife.business.user.model.User;
import org.springframework.stereotype.Controller;

/**
 * 地图操作Controller
 * @ClassName MapController
 * @Author HeZiLong
 * @Data 2021/1/20 15:10
 */
@Controller
public class GameMapController extends BaseController {

    /**
     * 当一个用户角色进入某地图，进行该业务,向该地图中所有角色到达地图的消息
     * @param operaUser
     * @param request
     */
    public void userSwitchMap(User operaUser, CM_SwitchMap request){

    }

}
