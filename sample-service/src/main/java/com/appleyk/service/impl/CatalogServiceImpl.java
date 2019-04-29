package com.appleyk.service.impl;

import com.appleyk.core.common.PageResult;
import com.appleyk.core.common.ex.CommonException;
import com.appleyk.core.dict.ResultCode;
import com.appleyk.core.filter.GFilter;
import com.appleyk.core.helper.GPageHelper;
import com.appleyk.core.model.Catalog;
import com.appleyk.core.model.CatalogNode;
import com.appleyk.dao.entity.CatalogEntity;
import com.appleyk.dao.entity.CatalogNodeEntity;
import com.appleyk.dao.mapper.IdMapper;
import com.appleyk.dao.mapper.catalog.CatalogMapper;
import com.appleyk.dao.mapper.catalog.CatalogNodeMapper;
import com.appleyk.service.CatalogService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>实现目录数据的增删改查</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:25 2019-4-27
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private IdMapper idMapper;

    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private CatalogNodeMapper nodeMapper;

    @Override
    @Transactional(rollbackFor = {CommonException.class, SQLException.class})
    public Catalog create(Catalog catalog) throws CommonException {
        return null;
    }

    @Override
    @Transactional(rollbackFor = {CommonException.class, SQLException.class})
    public Catalog create(Catalog catalog,Long rootId) throws CommonException {

        catalog.setId(idMapper.getObjectId());
        CatalogEntity entity = CatalogEntity.createEntity(catalog);
        entity.setRootId(rootId);
        int insert = catalogMapper.insert(entity);
        if(insert > 0){
            return catalog;
        }
        throw new CommonException(ResultCode.DATA_CREATE_ERROR,"目录创建失败");

    }

    @Override
    @Transactional(rollbackFor = {CommonException.class, SQLException.class})
    public Boolean delete(Long id) throws CommonException {

        Example example = new Example(CatalogEntity.class);
        example.and().andEqualTo("id",id);
        int i = catalogMapper.selectCountByExample(example);
        if(i == 0){
            throw new CommonException(ResultCode.OBJECT_NOT_EXIST,"要删除的目录不存在！");
        }

        // 如果存在，查看下目录id对应的节点有么有（即非空目录无法删除）
        example  = new Example(CatalogNodeEntity.class);
        example.and().andEqualTo("catalogId",id );
        i = nodeMapper.selectCountByExample(example);
        if(i > 0){
            throw new CommonException(ResultCode.DATA_DELETE_ERROR,"目录非空，无法删除！");
        }

        example = new Example(CatalogEntity.class);
        example.and().andEqualTo("id",id );
        int del = catalogMapper.deleteByExample(example);
        if(del > 0){
            return true;
        }

        throw new CommonException(ResultCode.DATA_DELETE_ERROR,"删除目录失败！");
    }

    @Override
    @Transactional(rollbackFor = {CommonException.class, SQLException.class})
    public Catalog update(Catalog data) throws CommonException {

        Long id = data.getId();
        Example example = new Example(CatalogEntity.class);
        example.and().andEqualTo("id",id );
        List<CatalogEntity> catalogEntities = catalogMapper.selectByExample(example);

        if(catalogEntities.size() == 0){
            throw new CommonException(ResultCode.OBJECT_NOT_EXIST,"更新的目录不存在！" );
        }

        CatalogEntity entity = CatalogEntity.createEntity(data);
        entity.setmTime(System.currentTimeMillis());

        // 更新数据实体的时候，只更新字段不等于null的值
        int i = catalogMapper.updateByPrimaryKeySelective(entity);
        if(i > 0){
            return data;
        }
        throw new CommonException(ResultCode.DATA_UPDATE_ERROR,"更新目录失败！" );

    }

    @Override
    public Catalog query(GFilter gFilter) throws CommonException {

        Long id = gFilter.getId();
        Example example = new Example(CatalogEntity.class);
        example.and().andEqualTo("id", id);
        List<CatalogEntity> catalogEntities = catalogMapper.selectByExample(example);
        if(catalogEntities.size() == 0){
            throw new CommonException(ResultCode.OBJECT_NOT_EXIST,"目录对象不存在");
        }

        // 设置目录的父节点、名称
        Catalog catalog = new Catalog();
        catalog.setId(catalogEntities.get(0).getId());
        catalog.setName(catalogEntities.get(0).getName());
        catalog.setcTime(catalogEntities.get(0).getcTime());


        // 查询目录下面的子目录
        example = new Example(CatalogEntity.class);
        example.and().andEqualTo("parentId",id );
        catalogEntities = catalogMapper.selectByExample(example);
        List<Catalog> catalogs = new ArrayList<>();
        for (CatalogEntity entity : catalogEntities) {
            catalogs.add(CatalogEntity.createModel(entity));
        }

        // 查询目录下面的子节点
        example = new Example(CatalogNodeEntity.class);
        example.and().andEqualTo("catalogId", id);
        List<CatalogNodeEntity> catalogNodeEntities = nodeMapper.selectByExample(example);
        List<CatalogNode> catalogNodes = new ArrayList<>();
        for (CatalogNodeEntity entity : catalogNodeEntities){
            CatalogNode catalogNode = CatalogNodeEntity.createModel(entity);
            catalogNodes.add(catalogNode);
        }

        catalog.setChildren(catalogs);
        catalog.setCatalogNodes(catalogNodes);
        return catalog;
    }

    @Override
    public PageResult<Catalog> queryRoots(GFilter filter) throws CommonException {

        GPageHelper.pageIntercept(filter);
        Example example = new Example(CatalogEntity.class);
        example.and().andEqualTo("rootId",0 ).andEqualTo("parentId",0 );
        Page<CatalogEntity> gxCatalogEntities =(Page<CatalogEntity>) catalogMapper.selectByExample(example);
        List<Catalog> gxCatalogs = new ArrayList<>();
        for (CatalogEntity catalogEntity: gxCatalogEntities)  {
            gxCatalogs.add(CatalogEntity.createModel(catalogEntity));
        }
        return GPageHelper.buildPage(gxCatalogs,gxCatalogEntities);

    }
}
