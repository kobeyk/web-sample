package com.appleyk.controller.base;

import com.appleyk.core.common.ex.CommonException;
import com.appleyk.core.dict.ResultCode;
import com.appleyk.core.model.GObject;
import com.appleyk.core.utils.GeneralUtils;

/**
 * <p>抽象一个控制器的基类，目的是校验一些请求参数值（可控）</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 5:12 2019-4-28
 */
public class BaseController {

    /**
     * <p>检查对象的id和name -- 用于更新</p>
     * @param object 对象
     */
    protected void checkObject(GObject object) throws CommonException {
        checkId(object.getId());
        checkName(object);
    }


    /**
     * <p>检查ID是否为空，对于删除和修改的操作，id是必须要有的</p>
     *  @param object 对象
     */
    protected void checkId(GObject object) throws CommonException {
        Long id = object.getId();
        checkId(id);
    }

    /**
     * <p>检查ID是否为空，对于删除和修改的操作，id是必须要有的</p>
     *  @param id 对象id
     */
    protected void checkId(Long id) throws CommonException {
        if(GeneralUtils.isEmpty(id)){
            throw new CommonException(ResultCode.OBJECT_ID_NOT_NULL,"更新/删除时，对象的ID不允许空！");
        }
    }

    /**
     * <p>检查对象的名称是否为空 == 对于特定的业务，是必须要带上name的，如创建用户时，用户名是必填的</p>
     * @param object 对象
     */
    protected void checkName(GObject object) throws CommonException {

        String name = object.getName();
        if(GeneralUtils.isEmpty(name)){
            throw new CommonException(ResultCode.OBJECT_NAME_NOT_NULL,"对象名称不允许空！");
        }
    }

}
