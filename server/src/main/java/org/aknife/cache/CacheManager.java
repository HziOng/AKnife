package org.aknife.cache;

import org.aknife.dao.mysql.CacheDao;
import org.aknife.dao.mysql.impl.CacheDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 缓存管理器
 * @ClassName CacheManager
 * @Author HeZiLong
 * @Data 2021/1/14 13:09
 */
public class CacheManager {

    private static volatile CacheManager manager = null;

    private CacheManager(){}

    public static CacheManager getInstance(){
        if (manager == null){
            synchronized (CacheManager.class){
                if (manager == null){
                    manager = new CacheManager();
                }
            }
        }
        return manager;
    }

    private ConcurrentHashMap<Class, ConcurrentHashMap<? extends Serializable, ?>> cache = new ConcurrentHashMap<>();

    /**
     * 要更新的数据队列
     */
    private ConcurrentLinkedQueue<Object> updateQueue = new ConcurrentLinkedQueue<>();

    private CacheDao cacheDao ;

    @Autowired
    public void setCacheDao(CacheDao cacheDao) {
        this.cacheDao = cacheDao;
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

    public <K,V> void updateCache(V value){
        updateQueue.add(value);
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
            cacheDao.load(clazz, 1);
            classCache.put(key, value);
        }
        Integer i = 0;
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
     * 将数据更新到数据库中
     */
    public void synchronizeData(){
        cacheDao.updateList(updateQueue.toArray());
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
}
