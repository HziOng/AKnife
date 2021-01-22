package org.aknife.business.character.manager.impl;

import org.aknife.business.character.manager.CharacterManager;
import org.aknife.business.character.manager.CharacterManagerImpl;
import org.aknife.business.character.model.UserCharacter;
import org.aknife.business.user.manager.UserManager;
import org.aknife.business.user.manager.UserManagerImpl;
import org.aknife.business.user.model.User;
import org.aknife.business.user.util.UserUtil;
import org.aknife.business.map.model.Location;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CharacterManagerImplTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    CharacterManager characterManager = context.getBean(CharacterManagerImpl.class);
    UserManager userManager  = context.getBean(UserManagerImpl.class);

    @Test
    public void insert() {
        User user = new User();
        user.setUserID(UserUtil.getUUID());
        user.setUsername("14");
        user.setPassword("14");
        user.setStatus(0);
        UserCharacter character = new UserCharacter();
        character.setId(UserUtil.getCharacterId(user.getUserID(),1));
        character.setLocation(new Location(10,10,0));
        user.setCharacterId(character.getId());

        characterManager.updateCharacter(character);
        userManager.addUser(user);
    }
}