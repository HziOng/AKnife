package org.aknife.cache;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 缓存管理器
 * @ClassName CacheManager
 * @Author HeZiLong
 * @Data 2021/1/14 13:09
 */
@Repository
public class CacheManager {

    private static final int SURVIVAL_TIME = 10 * 60 * 1000;

    private HashMap<Class, HashMap<? extends Serializable, Node>> cache = new HashMap<>();

    /**
     * 要更新的数据队列
     */
    private ConcurrentLinkedQueue<Object> updateQueue = new ConcurrentLinkedQueue<>();

    /**
     * 要新增的数据队列
     */
    private ConcurrentLinkedQueue<Object> addQueue = new ConcurrentLinkedQueue<>();

    /**
     * 这里让过期时间最小的数据排在队列前面
     */
    private HashMap<Class, PriorityQueue<Node>> expireQueue = new HashMap<>();

    private CacheDao cacheDao ;

    // 定时任务
    /**
     * 更新数据
     */
    private TimerManager timerManager;

    /**
     * 过期删除任务
     */
    private ExpiredNodeManager expiredManager;

    @Autowired
    public void setCacheDao(CacheDao cacheDao) {
        this.cacheDao = cacheDao;
    }

    public CacheManager(){
        timerManager = new TimerManager(this);
        expiredManager = new ExpiredNodeManager(this);
    }

    /**
     * 返回指定model类型的所有缓存
     * @param clazz
     * @return
     */
    private  <K extends Serializable,V> HashMap<K, Node> getCache(Class clazz){
        if (!cache.containsKey(clazz)){
            cache.putIfAbsent(clazz, new HashMap<>());
            expireQueue.put(clazz, new PriorityQueue());
        }
//        cache.computeIfAbsent()
        return (HashMap<K, Node>) cache.get(clazz);
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

    /**
     * 立刻将该数据更新到数据库中
     * @param value
     * @param <K>
     * @param <V>
     */
    public <K extends Serializable,V> void updateCacheNow(V value){

    }

    /**
     * 向缓存中添加数据，并准备更新到数据库中
     * @param clazz
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     */
    public <K extends Serializable,V> void addCacheIfAbsent(Class clazz, K key, V value){
        Node now = new Node(key,value,System.currentTimeMillis()+SURVIVAL_TIME
        );
        HashMap<K,Node> map = getCache(clazz);
        synchronized (clazz){
            map.putIfAbsent(key,now);
            expireQueue.get(clazz).add(now);
            addQueue.add(value);
        }
    }

    /**
     * 向缓存中添加数据，但是不更新到数据库中,这个一般是其他业务中直接访问数据库获取数据后，手动添加到缓存中
     * @param clazz
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     */
    public <K extends Serializable,V> void addCacheIfAbsentNoUpdateToMySQL(Class clazz, K key, V value){
        Node now = new Node(key,value,System.currentTimeMillis()+SURVIVAL_TIME);
        HashMap<K,Node> map = getCache(clazz);
        map.putIfAbsent(key,now);
        expireQueue.get(clazz).add(now);
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
        HashMap<K,Node> classCache = getCache(clazz);
        synchronized (clazz) {
            Node now = classCache.get(key);
            V value = now == null ? null : (V) now.value;
            if (value == null) {
                value = cacheDao.load(clazz, key);
                if (value == null) {
                    return null;
                }
                classCache.put(key, new Node(key, value, System.currentTimeMillis() + SURVIVAL_TIME));
            } else {
                now.expireTime = System.currentTimeMillis() + SURVIVAL_TIME;
            }
            return value;
        }
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
        if (!updateQueue.isEmpty()){
            cacheDao.updateList(updateQueue.toArray());
            updateQueue.clear();
        }
        if (!addQueue.isEmpty()) {
            cacheDao.addList(addQueue.toArray());
            addQueue.clear();
        }
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

    private class TimerManager{
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

    private class ExpiredNodeManager {

        private static final long PERIOD_DAY = 1000;

        public ExpiredNodeManager(CacheManager manager) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    long now = System.currentTimeMillis();
                    for (Class clazz : expireQueue.keySet()) {
                        while (true) {
                            synchronized (clazz) {
                                Node node = manager.expireQueue.get(clazz).peek();
                                //没有数据了，或者数据都是没有过期的了
                                if (node == null || node.expireTime > now) {
                                    break;
                                }
                                manager.getCache(node.getValue().getClass()).remove(node.key);
                                manager.expireQueue.get(clazz).poll();
                            }
                        }
                    }
                }
            }, PERIOD_DAY, PERIOD_DAY);
        }
    }

    @Data
    private static class Node<K,V> implements Comparable<Node>{

        private K key;

        private V value;

        private long expireTime;

        public Node(K key, V value, long expireTime) {
            this.key = key;
            this.value = value;
            this.expireTime = expireTime;
        }

        @Override
        public int compareTo(Node o) {
            long r = this.expireTime - o.expireTime;
            if (r > 0) {
                return 1;
            }
            if (r < 0) {
                return -1;
            }
            return 0;
        }
    }
}
