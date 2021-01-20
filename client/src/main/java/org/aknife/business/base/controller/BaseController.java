package org.aknife.business.base.controller;

import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.user.model.User;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BaseController
 * @Author HeZiLong
 * @Data 2021/1/13 11:14
 */
public class BaseController {

    protected static User user = new User();

    protected static ConcurrentHashMap<Integer, UserCharacter> characters = new ConcurrentHashMap<>();

}
