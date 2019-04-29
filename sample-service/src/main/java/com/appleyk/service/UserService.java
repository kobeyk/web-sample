package com.appleyk.service;

import com.appleyk.core.common.PageResult;
import com.appleyk.core.filter.GFilter;
import com.appleyk.core.model.GUser;
import com.appleyk.core.service.IDataManager;

/**
 * <p>用户接口定义</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:16 2019-4-28
 */
public interface  UserService extends IDataManager<GUser>{


    /**
     * <p>分页查询用户</p>
     * @param filter 用户令牌
     * @return PageResult 分页结果
     */
    PageResult<GUser> query(GFilter filter);
}
