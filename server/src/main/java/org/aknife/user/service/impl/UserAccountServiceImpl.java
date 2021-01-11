package org.aknife.user.service.impl;

import lombok.extern.java.Log;
import org.aknife.common.PacketFixedData;
import org.aknife.user.packet.CM_UserLogin;
import org.aknife.user.service.IUserAccountService;
import org.aknife.util.annotation.Operating;
import org.springframework.stereotype.Service;

/**
 * 用户账号操作业务
 * @ClassName UserAccountServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/11 15:17
 */
@Service
@Log
public class UserAccountServiceImpl implements IUserAccountService {
    @Override
    @Operating(PacketFixedData.CM_USERREGISTER)
        public int register(CM_UserLogin data) {
        log.info("运行注册方法");
        return 0;
    }

    @Override
    @Operating(PacketFixedData.CM_USERLOGIN)
    public int login(CM_UserLogin data) {
        log.info("运行登录方法");
        return 0;
    }
}
