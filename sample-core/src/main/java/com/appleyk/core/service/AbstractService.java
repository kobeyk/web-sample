package com.appleyk.core.service;

/**
 * <p>抽象服务类 - 实现数据管理接口 -- 所有继承该类的子类都将添加到全局服务实例容器中</p>
 * <p>好处，不用以注入bean的方式实现service层的调用</p>
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:28 2019-4-27
 */
public abstract class AbstractService implements ITokenManager{


    /**
     * 只要子类被实例化，首先调用的就是父类的这个构造器
     */
    public AbstractService(){
        ServiceContainer.addServiceInstance(this);
    }


    /**
     * 抽象方法 -- 获取当前服务实例的名称
     * @return String
     */
    protected  abstract String getServiceName();


}
