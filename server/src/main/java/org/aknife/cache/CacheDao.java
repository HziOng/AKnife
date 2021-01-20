package org.aknife.cache;

import java.io.Serializable;

/**
 * @ClassName BaseMySqlCacheDao
 * @Author HeZiLong
 * @Data 2021/1/14 17:12
 */
public interface CacheDao {


    /**
     *
     * @param clazz
     * @param key
     * @param <K>
     * @param <V>
     * @return
     */
    <K extends Serializable,V> V load(Class clazz, K key);

    /**
     * 更新数据
     * @param value
     * @param <V>
     */
    <V> void update(V value);

    /**
     * 更新数组集合
     * @param values
     */
    void updateList(Object[] values);

    /**
     * 将数据集合增加到数据库中
     * @param values
     */
    void addList(Object[] values);

    /**
     * 新增数据
     * @param value
     * @param <K>
     * @param <V>
     */
    <K,V> void save(V value);
}
