package com.madrix.dao;

import java.util.List;

/**
 * 基础dao接口
 * 该接口中定义了dao接口中的基本方法，所有的dao接口都是从此接口衍生出来
 * Created by sdc on 2018/2/26.
 */
public interface BaseDao<T> {
    /**
     * 添加方法
     * @param t
     * @return
     */
    int insert(T t);

    /**
     * 根据id删除记录
     * @param id
     * @return
     */
    int delete(int id);

    /**
     * 批量根据id删除记录
     * @param id
     * @return
     */
    int delete(int[] id);

    /**
     * 更新记录
     * 实体属性不为空的时候就更新
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 根据属性查询
     * @param t
     * @return
     */
    List<T> findByProperty(T t);

}
