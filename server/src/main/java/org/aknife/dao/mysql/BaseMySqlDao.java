package org.aknife.dao.mysql;

import org.aknife.dao.BaseDao;

/**
 * @ClassName BaseMySqlDao
 * @Author HeZiLong
 * @Data 2021/1/12 14:54
 */
public interface BaseMySqlDao<T> extends BaseDao {

    /**
     * 向数据库中添加T
     * @param t 要添加的对象数据
     */
    void add(T t);

    /**
     * 删除数据库中符合条件T中属性的数据
     * @param t 要珊瑚的对象属性
     */
    void delete(T t);

    /**
     * 根据用户id查询用户
     * @param id
     * @return 返回指定id的T对象
     */
    T find(int id);

    /**
     * 修改该数据对象
     * @param t
     */
    void update(T t);
}
