package org.aknife.business.user.service.impl;

import lombok.extern.java.Log;
import org.aknife.business.user.character.manager.CharacterManager;
import org.aknife.business.user.character.model.UserCharacter;
import org.aknife.business.user.manager.UserManager;
import org.aknife.business.user.util.UserUtil;
import org.aknife.business.user.constant.UserStatusConsts;
import org.aknife.business.base.exception.GlobalException;
import org.aknife.business.user.model.User;
import org.aknife.business.user.service.IUserAccountService;
import org.aknife.constant.ProtocolFixedData;
import org.aknife.resource.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户账号操作业务
 * @ClassName UserAccountServiceImpl
 * @Author HeZiLong
 * @Data 2021/1/11 15:17
 */
@Log
@Service
public class UserAccountServiceImpl implements IUserAccountService {

    private UserManager userManager;

    private CharacterManager characterManager;

    @Autowired
    public void setCharacterManager(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public int register(User operaUser) throws GlobalException {
        log.info("用户[ "+operaUser+" ]运行注册方法");
        if (operaUser == null){
            throw new GlobalException("输入用户数据为空");
        }
        User now = userManager.queryUserByName(operaUser.getUsername());
        if (now != null){
            throw new GlobalException("该用户名已被注册");
        }
        // 新建角色
        UserCharacter character = new UserCharacter();
        character.setId(UserUtil.getCharacterId(operaUser.getUserID(),1));
        character.setMapID(0);
        character.setLocation(new Location(10,10,0));
        operaUser.setCharacterId(character.getId());
        operaUser.getCharacterIds().add(character.getId());
        userManager.addUser(operaUser);
        return ProtocolFixedData.STATUS_OK;
    }

    @Override
    public int login(User operaUser) throws GlobalException {
        log.info("用户[ "+operaUser+" ]运行登录方法");
        if (operaUser == null){
            throw new GlobalException("输入用户数据为空");
        }
        User now  = userManager.queryUserByName(operaUser.getUsername());
        if (now == null){
            throw new GlobalException("该用户不存在，请注册");
        }
        if (now.getStatus() == UserStatusConsts.ON_LINE){
            throw new GlobalException("该用户已经登录，确定强制登录");
        }
        if (!now.getPassword().equals(operaUser.getPassword())) {
            throw new GlobalException("用户密码错误");
        }
        now.setStatus(UserStatusConsts.ON_LINE);
        UserUtil.userCopyToUser(now, operaUser);
        userManager.updateUser(now);
        return ProtocolFixedData.STATUS_OK;
    }

    @Override
    public int dropOut(User operaUser) throws GlobalException {
        log.info("用户[ "+operaUser+" ]运行退出方法");
        return ProtocolFixedData.STATUS_OK;
    }

}
