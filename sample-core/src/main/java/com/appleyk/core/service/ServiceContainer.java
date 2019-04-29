package com.appleyk.core.service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>全局服务实例容器</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:28 2019-4-27
 */
public class ServiceContainer  {

    private static Map<String,AbstractService> serviceList = new HashMap<>(16);

    synchronized static void addServiceInstance(AbstractService aService){
        serviceList.put(aService.getServiceName(),aService);
    }

    synchronized public static AbstractService getServiceInstance(String serviceName){
        return serviceList.get(serviceName);
    }

}
