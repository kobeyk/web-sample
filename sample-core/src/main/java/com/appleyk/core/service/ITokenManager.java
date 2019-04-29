package com.appleyk.core.service;

import com.appleyk.core.common.ex.CommonException;

/**
 * <p>用户令牌管理</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 7:19 2019-4-28
 */
public interface ITokenManager {

    /**
     * <p>检查token</p>
     * @param token 用户令牌
     * @return 能解出id，返回true，否则返回false
     */
    Boolean checkToken(String token) throws CommonException;
}
