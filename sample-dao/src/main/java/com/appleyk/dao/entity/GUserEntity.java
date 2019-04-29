package com.appleyk.dao.entity;

import com.appleyk.core.dict.UserRoleEnum;
import com.appleyk.core.model.GUser;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * <p>用户数据实体类</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 6:19 2019-4-27
 */
@Table(name = "yk_user")
public class GUserEntity {

    @Id
    private Long id;
    private String name;
    private String avatar;

    @Column(name = "telphone")
    private String telPhone;

    @Column(name = "ctime")
    private Long cTime;

    private Integer role;

    private GUserEntity(){

    }

    private GUserEntity(GUser user){
        this.id = user.getId();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.telPhone = user.getTelPhone();
        this.cTime = user.getcTime() == null ? System.currentTimeMillis() : user.getcTime().getTime();
        this.role = user.getRole().getCode();
    }

    /**
     * 根据业务模型创建数据实体对象
     * @param gUser 用户业务模型
     * @return GUserEntity
     */
    public static GUserEntity createEntity(GUser gUser){
        return new GUserEntity(gUser);
    }

    /**
     * 根据数据实体对象创建业务模型
     * @param entity 数据实体对象
     * @return GUser
     */
    public static GUser createModel(GUserEntity entity){
        GUser user = new GUser(entity.getId(),
                entity.getName(), entity.getAvatar(),
                entity.getTelPhone(), new Date(entity.getcTime()));
        user.setRole(UserRoleEnum.getEnum(entity.getRole()));
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getcTime() {
        return cTime;
    }

    public void setcTime(Long cTime) {
        this.cTime = cTime;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
