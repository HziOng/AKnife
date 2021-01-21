package org.aknife.business.map.controller;

import org.aknife.business.base.controller.BaseController;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.map.packet.SM_OtherUserAwayMap;
import org.aknife.business.map.packet.SM_OtherUserEntryMap;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.connection.annotation.Operating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 客户端地图控制
 * @ClassName GameMapController
 * @Author HeZiLong
 * @Data 2021/1/21 17:13
 */
@Controller
public class GameMapController extends BaseController {

    private IGameMapService gameMapService;

    @Autowired
    public void setGameMapService(IGameMapService gameMapService) {
        this.gameMapService = gameMapService;
    }

    /**
     * 其他用户进入该地图，本用户进行业务处理
     * @param response
     */
    @Operating
    public void otherUserEntryMap(SM_OtherUserEntryMap response){
        try {
            gameMapService.userEntryMap(response.getUserId(), response.getUsername(), response.getCharacterId());
        } catch (GlobalException e){
            e.printStackTrace();
        }
    }

    /**
     * 其他用户离开该地图，本用户进行业务处理
     * @param response
     */
    @Operating
    public void otherUserAwayMap(SM_OtherUserAwayMap response){
        try {
            gameMapService.userGoAwayMap(response.getUserId(), response.getUsername());
        } catch (GlobalException e){
            e.printStackTrace();
        }
    }
}
