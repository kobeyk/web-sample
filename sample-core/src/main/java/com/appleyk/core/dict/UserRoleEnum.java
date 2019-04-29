package com.appleyk.core.dict;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>用户角色枚举</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 1:48 2019-4-28
 */
public enum UserRoleEnum {

    /**
     * 三种角色：
     * 1、普通用户 -- 只读
     * 2、管理员 -- 读和写
     * 3、超级管理员 -- 读、写、删除
     */
    COMMON(2,"常规用户"),
    ADMIN(1,"管理员"),
    SUPER_ADMIN(0,"超级管理员");

    /**
     * 枚举码  -- 字段的类型统一为包装类型
     */
    private final Integer code;

    /**
     * 枚举值
     */
    private final String name;

    UserRoleEnum(final Integer code , final String name){
        this.code = code;
        this.name = name;
    }

    /**
     * <p>序列化成json时，该方法会被调用</p>
     * @param code 用户角色码
     * @return 用户角色类型
     */
    @JsonCreator
    public static UserRoleEnum getEnum(Integer code){

        UserRoleEnum[] values = UserRoleEnum.values();
        for (UserRoleEnum roleEnum : values) {
            if(code.equals(roleEnum.getCode())){
                return roleEnum;
            }
        }
        return null;
    }


    @JsonValue
    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
