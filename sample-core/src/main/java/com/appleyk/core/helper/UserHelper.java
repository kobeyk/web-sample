package com.appleyk.core.helper;

import com.appleyk.core.service.AbstractService;
import com.appleyk.core.service.ServiceContainer;

/**
 * <p>用户帮助类 -- 模拟Controller无需注入bean，只需要调用该类的create方法既能实现用户的插入</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:47 2019-4-27
 */
public class UserHelper {

    /**
     * <p>调用具体的服务实例，来实现token的验证功能</p>
     * @param token
     * @return
     * @throws Exception
     */
    public static boolean checkToken(String token) throws Exception{
        AbstractService user = ServiceContainer.getServiceInstance("guser");
        return user.checkToken(token);
    }
}
