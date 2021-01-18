package org.aknife.cache;

import org.aknife.business.user.entity.UserEntity;
import org.aknife.business.user.manager.UserManager;
import org.aknife.business.user.manager.impl.UserManagerImpl;
import org.aknife.business.user.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
class CacheManagerTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    CacheManager manager = (CacheManager) context.getBean("cacheManager");

    @Test
    void updateCache() {
        UserEntity now = manager.getClassObject(UserEntity.class, 210115561);
        now.setUserName("12");
        manager.updateCache(now);
        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(now);
    }

    @Test
    void refreshData() {
        UserEntity now = manager.getClassObject(UserEntity.class, 210115561);
        System.out.println(now);
        now.setUserName("14");
        now.getUser().setUsername("14");
        manager.refreshData(now);

        now = manager.getClassObject(UserEntity.class, 210115561);
        System.out.println(now);

        System.out.println();
    }


    @Test
    void synchronizeData() {
        UserEntity now = manager.getClassObject(UserEntity.class, 210115561);
        now.setUserName("14");
        manager.updateCache(now);
        manager.synchronizeData();
    }
}