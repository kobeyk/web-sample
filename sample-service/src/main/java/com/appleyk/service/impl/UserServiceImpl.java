package com.appleyk.service.impl;

import com.appleyk.core.common.PageResult;
import com.appleyk.core.common.ex.CommonException;
import com.appleyk.core.dict.ResultCode;
import com.appleyk.core.dict.UserRoleEnum;
import com.appleyk.core.filter.GFilter;
import com.appleyk.core.helper.GPageHelper;
import com.appleyk.core.helper.GxFilterHelper;
import com.appleyk.core.helper.TokenHelper;
import com.appleyk.core.model.GUser;
import com.appleyk.core.service.AbstractService;
import com.appleyk.dao.entity.GUserEntity;
import com.appleyk.dao.mapper.IdMapper;
import com.appleyk.dao.mapper.guser.GUserMapper;
import com.appleyk.dao.utils.MapperUtils;
import com.appleyk.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>用户接口实现+继承，完成用户数据的增删改查以及用户令牌的验证</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:19 2019-4-28
 */
@Service
public class UserServiceImpl  extends AbstractService implements UserService {

    @Autowired
    private IdMapper idMapper;

    @Autowired
    private GUserMapper userMapper;

    private static final String SERVICE_NAME = "guser";

    @Override
    @Transactional(rollbackFor = {CommonException.class, SQLException.class})
    public GUser create(GUser user) throws CommonException {

        boolean nameIsExist = MapperUtils.nameIsExist(userMapper, GUserEntity.class, user.getName());
        if (nameIsExist){
            throw new CommonException(ResultCode.OBJECT_NAME_REPEATED,"用户名重复");
        }

        user.setId(idMapper.getObjectId());
        GUserEntity entity = GUserEntity.createEntity(user);
        int insert = userMapper.insert(entity);
        if(insert > 0){
            return user;
        }
        throw new CommonException(ResultCode.DATA_CREATE_ERROR,"用户创建失败！");
    }

    @Override
    public Boolean delete(Long id) throws CommonException {

        GUserEntity userEntity = MapperUtils.objectIsExistById(userMapper, GUserEntity.class, id, true);
        if(userEntity == null){
            throw new CommonException(ResultCode.OBJECT_NOT_EXIST,"删除的用户不存在！");
        }

        // 验证是不是超管,如果超管的话，不让删除
        Integer role = userEntity.getRole();
        if(UserRoleEnum.SUPER_ADMIN.equals(UserRoleEnum.getEnum(role))){
            throw new CommonException(ResultCode.DATA_DELETE_ERROR,"无法删除超级管理员！");
        }

        int i = userMapper.delete(userEntity);
        if(i > 0){
            return true;
        }
        throw new CommonException(ResultCode.DATA_DELETE_ERROR,"删除用户失败！" );

    }

    @Override
    public GUser update(GUser user) throws CommonException {

        boolean isExistById = MapperUtils.objectIsExistById(userMapper, GUserEntity.class,"id", user.getId());
        if(!isExistById){
            throw new CommonException(ResultCode.OBJECT_NOT_EXIST,"更新的用户不存在！");
        }
        GUserEntity entity = GUserEntity.createEntity(user);
        int i = userMapper.updateByPrimaryKeySelective(entity);
        if(i > 0){
            return user;
        }
        throw new CommonException(ResultCode.DATA_DELETE_ERROR,"更新用户失败！" );

    }

    @Override
    public PageResult<GUser> query(GFilter filter) {

        GPageHelper.pageIntercept(filter);
        Example example = GxFilterHelper.buildQueryExample(filter, GUserEntity.class);
        Page<GUserEntity> gUserEntities = (Page<GUserEntity>) userMapper.selectByExample(example);
        List<GUser> users = new ArrayList<>();
        for (GUserEntity gUserEntity : gUserEntities) {
            users.add(GUserEntity.createModel(gUserEntity));
        }
        return GPageHelper.buildPage(users,gUserEntities);

    }


    /**
     * 简单实现用户令牌的验证，不涉及token缓存及过期，不涉及用户是否在数据库中存在，仅能反解出来拿到uid即可
     * @param token 用户令牌
     */
    @Override
    public Boolean checkToken(String token) throws CommonException{
        Long uid = TokenHelper.verifyToken(token);
        System.out.println("解析token获取uid = " + uid);
        return !(uid == null || uid == 0);
    }

    @Override
    protected String getServiceName() {
        return SERVICE_NAME;
    }
}
