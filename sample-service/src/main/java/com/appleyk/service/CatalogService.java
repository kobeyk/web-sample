package com.appleyk.service;

import com.appleyk.core.common.PageResult;
import com.appleyk.core.common.ex.CommonException;
import com.appleyk.core.filter.GFilter;
import com.appleyk.core.model.Catalog;
import com.appleyk.core.service.IDataManager;

/**
 * <p>目录接口 -- 继承通用数据管理接口</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:15 2019-4-27
 */
public interface CatalogService extends IDataManager<Catalog> {

    // 如果有需求，自行扩展接口


    /**
     * <p>根据过滤器查询目录</p>
     * @param gFilter
     * @return List<Catalog>
     */
    Catalog query(GFilter gFilter) throws CommonException;

    /**
     * <p>根据基础过滤器查询目录树的根节点</p>
     * @param filter 基础过滤器条件对象
     * @return 根目录列表
     */
    PageResult<Catalog> queryRoots(GFilter filter) throws CommonException;


    /**
     * <p>创建新的目录</p>
     * @param catalog 目录业务对象
     * @param rootId 树的根ID
     * @return Catalog
     */
    Catalog create(Catalog catalog, Long rootId) throws CommonException;


}
