package org.aknife.business.map.controller;

import org.aknife.business.base.controller.BaseController;
import org.aknife.business.map.packet.SM_MoveLocation;
import org.aknife.business.map.service.ICharacterLocationService;
import org.aknife.connection.annotation.Operating;
import org.aknife.constant.ProtocolFixedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @ClassName CharacterLocationController
 * @Author HeZiLong
 * @Data 2021/1/21 10:13
 */
@Controller
public class CharacterLocationController extends BaseController {

    private ICharacterLocationService locationService;

    @Autowired
    public void setLocationService(ICharacterLocationService locationService) {
        this.locationService = locationService;
    }

    @Operating
    public void moveLocation(SM_MoveLocation response){
        if (response.getStatus() == ProtocolFixedData.STATUS_OK) {
            locationService.moveLocation();
        }else {
            locationService.movePlaceFailure(response.getMessage());
        }
    }
}
