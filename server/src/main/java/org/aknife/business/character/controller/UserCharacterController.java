package org.aknife.business.character.controller;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.aknife.business.base.controller.BaseController;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.character.service.IUserCharacterService;
import org.aknife.business.map.controller.GameMapController;
import org.aknife.business.map.service.GameMapServiceImpl;
import org.aknife.business.map.service.IGameMapService;
import org.aknife.business.user.model.User;
import org.aknife.business.character.packet.CM_SwitchMap;
import org.aknife.business.character.packet.SM_SwitchMap;
import org.aknife.connection.thread.CommonOperationThreadUtil;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.connection.annotation.Operating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Method;

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

    private IGameMapService gameMapService;

    @Autowired
    public void setGameMapService(IGameMapService gameMapService) {
        this.gameMapService = gameMapService;
    }

    @Autowired
    public void setUserCharacterService(IUserCharacterService userCharacterService) {
        this.userCharacterService = userCharacterService;
    }

}
