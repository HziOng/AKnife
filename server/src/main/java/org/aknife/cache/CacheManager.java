package org.aknife.cache;

import org.aknife.dao.mysql.CacheDao;
import org.aknife.dao.mysql.impl.CacheDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 缓存管理器
 * @ClassName CacheManager
 * @Author HeZiLong
 * @Data 2021/1/14 13:09
 */
@Repository
public class CacheManager {

    private ConcurrentHashMap<Class, ConcurrentHashMap<? extends Serializable, ?>> cache = new ConcurrentHashMap<>();

    /**
     * 要更新的数据队列
     */
    private ConcurrentLinkedQueue<Object> updateQueue = new ConcurrentLinkedQueue<>();

    private CacheDao cacheDao ;

    private TimerManager timerManager;

    @Autowired
    public void setCacheDao(CacheDao cacheDao) {
        this.cacheDao = cacheDao;
    }

    public CacheManager(){
        timerManager = new TimerManager(this);
    }

    /**
     * 返回指定model类型的所有缓存
     * @param clazz
     * @return
     */
    public <K extends Serializable,V> ConcurrentHashMap<K, V> getCache(Class clazz){
        if (!cache.containsKey(clazz)){
            cache.put(clazz, new ConcurrentHashMap<K, V>());
        }
        return (ConcurrentHashMap<K, V>) cache.get(clazz);
    }

    /**
     * 更新缓存
     * @param value
     * @param <K>
     * @param <V>
     */
    public <K extends Serializable,V> void updateCache(V value){
        updateQueue.add(value);
    }

    public <K extends Serializable,V> void addCacheIfAbsent(Class clazz, K key, V value){
        getCache(clazz).putIfAbsent(key,value);
    }
    /**
     * 根据model类型、属性ID查询缓存数据
     * @param clazz
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    public <K extends Serializable,V> V getClassObject(Class clazz, K key){
        ConcurrentHashMap<K,V> classCache = getCache(clazz);
        V value =  classCache.get(key);
        if (value == null){
            value = cacheDao.load(clazz, key);
            if (value == null){
                return null;
            }
            classCache.put(key, value);
        }
        return value;
    }

    /**
     * 删除缓存中clazz类型的指定key的数据
     * @param clazz
     * @param key
     * @param <K>
     */
    public <K extends Serializable, V> void deleteCache(Class clazz, K key){
        updateQueue.add(remove(clazz,key));
    }

    /**
     * 立刻将数据更新到数据库中
     */
    public void synchronizeData(){
        if (updateQueue.isEmpty()){
            return;
        }
        cacheDao.updateList(updateQueue.toArray());
        updateQueue.clear();
    }

    /**~
     * 将缓存中的value立刻刷新到数据库中
     * @param value
     * @param <V>
     */
    public <V> void refreshData(V value){
        cacheDao.update(value);
    }

    /**
     * 直接从缓存中删除clazz类型中key对应的数据，注意，此方法不安全，该数据删除前可能未同步数据库
     * @param clazz
     * @param key
     * @param <K>
     * @param <V>
     * @return 返回被删除的数据
     */
    protected <K extends Serializable, V> V remove(Class clazz, K key){
        return (V) getCache(clazz).remove(key);
    }

    class TimerManager{
        private static final long PERIOD_DAY = 1000;  //每隔六十秒执行一次
        public TimerManager(CacheManager manager) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    manager.synchronizeData();
                }
            }, PERIOD_DAY, PERIOD_DAY);
        }
    }
}
