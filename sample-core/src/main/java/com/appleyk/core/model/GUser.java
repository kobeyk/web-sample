package com.appleyk.core.model;

import com.appleyk.core.dict.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>用户业务模型</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 6:15 2019-4-27
 */
public class GUser extends GObject{

    /**
     * 用户头像地址
     */
    private String avatar;

    /**
     * 用户联系方式
     */
    private String telPhone;

    /**
     * 用户角色 -- 默认为普通用户
     */
    private UserRoleEnum role = UserRoleEnum.COMMON;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cTime = new Date();

    public GUser(){

    }

    public GUser(Long id,String name,String avatar, String telPhone, Date cTime) {
        super(id,name);
        this.avatar = avatar;
        this.telPhone = telPhone;
        this.cTime = cTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }
}
