package com.appleyk.core.service;

import com.appleyk.core.common.ex.CommonException;

/**
 * <p>通用数据增删改接口</p>
 *
 * @author yukun24@126.com
 * @version V.1.0.1
 * @company 苏州中科蓝迪
 * @date created on 下午 2:21 2019-4-18
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
