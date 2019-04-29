package com.appleyk.service.impl;

import com.appleyk.core.common.ex.CommonException;
import com.appleyk.core.dict.ResultCode;
import com.appleyk.core.model.CatalogNode;
import com.appleyk.dao.entity.CatalogEntity;
import com.appleyk.dao.entity.CatalogNodeEntity;
import com.appleyk.dao.mapper.IdMapper;
import com.appleyk.dao.mapper.catalog.CatalogNodeMapper;
import com.appleyk.dao.utils.MapperUtils;
import com.appleyk.service.CatalogNodeService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>目录节点实现类</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:37 2019-4-28
 */
@Service
public class CatalogNodeServiceImpl implements CatalogNodeService {

    @Autowired
    private IdMapper idMapper;

    @Autowired
    private CatalogNodeMapper nodeMapper;


    @Override
    @Transactional(rollbackFor = {CommonException.class, SQLException.class})
    public CatalogNode create(CatalogNode catalogNode) throws CommonException {

        // 判断下，同目录下是否存在相同的文件
        judgeNode(catalogNode);
        catalogNode.setId(idMapper.getObjectId());
        CatalogNodeEntity entity = CatalogNodeEntity.createEntity(catalogNode);
        entity.setmTime(System.currentTimeMillis());
        int insert = nodeMapper.insert(entity);
        if(insert>0){
            return catalogNode;
        }
        throw new CommonException(ResultCode.DATA_CREATE_ERROR,"目录节点创建失败");
    }


    @Override
    @Transactional(rollbackFor = {CommonException.class, SQLException.class})
    public Boolean delete(Long id) throws CommonException {

        CatalogNodeEntity entity = MapperUtils.objectIsExistById(nodeMapper, CatalogNodeEntity.class, id, true);
        if(entity == null){
            throw new CommonException(ResultCode.OBJECT_NOT_EXIST,"要删除的目录节点不存在！");
        }
        int del = nodeMapper.delete(entity);
        if(del > 0){
            return true;
        }

        throw new CommonException(ResultCode.DATA_DELETE_ERROR,"删除目录节点失败！");
    }

    @Override
    @Transactional(rollbackFor = {CommonException.class, SQLException.class})
    public CatalogNode update(CatalogNode catalogNode) throws CommonException {

        Long id = catalogNode.getId();
        boolean isExistById = MapperUtils.objectIsExistById(nodeMapper, CatalogNodeEntity.class,
                "id", id);
        if(!isExistById){
            throw new CommonException(ResultCode.OBJECT_NOT_EXIST,"更新的目录节点不存在！" );
        }
        judgeNode(catalogNode);
        CatalogNodeEntity entity = CatalogNodeEntity.createEntity(catalogNode);
        // 更新数据实体的时候，只更新字段不等于null的值
        int i = nodeMapper.updateByPrimaryKeySelective(entity);
        if(i > 0){
            return catalogNode;
        }
        throw new CommonException(ResultCode.DATA_UPDATE_ERROR,"更新目录节点失败！" );
    }


    /**
     * <p>目录节点验证</p>
     * @param catalogNode
     */
    private void judgeNode(CatalogNode catalogNode) throws CommonException {
        // 判断下，同目录下是否存在相同的文件
        Example example = new Example(CatalogNodeEntity.class);
        example.and().andEqualTo("name",catalogNode.fetchObjName())
                .andEqualTo("catalogId",catalogNode.getCatalogId());
        int i = nodeMapper.selectCountByExample(example);
        if(i > 0){
            throw new CommonException(ResultCode.OBJECT_IS_EXIST,"目录下已存在同名对象");
        }
    }
}
