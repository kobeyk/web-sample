package com.appleyk.core.service;

import com.appleyk.core.common.ex.CommonException;

/**
 * <p>通用数据增删改接口</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:28 2019-4-27
 */
public interface IDataManager<T> {


    /**
     * <p>创建数据</p>
     * @param data 业务模型对象
     */
    T create(T data) throws CommonException;

    /**
     *<p>根据对象ID删除数据</p>
     * @param id  对象ID
     */
    Boolean delete(Long id) throws CommonException;

    /**
     *<p>更新数据</p>
     * @param data  业务模型对象
     */
    T update(T data) throws CommonException;
}
