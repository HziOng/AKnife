package org.aknife.business.base.manager;

import javax.annotation.PostConstruct;

/**
 * @ClassName BaseManager
 * @Author HeZiLong
 * @Data 2021/1/26 11:40
 */
public interface BaseManager {

    /**
     * 所有BaseManager的初始化方法
     */
    @PostConstruct
    default void initMethod(){

    }
}
