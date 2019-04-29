package com.appleyk.core.helper;

import com.appleyk.core.common.PageResult;
import com.appleyk.core.filter.GFilter;
import com.appleyk.core.utils.GeneralUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * <p>分页帮助类</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 3:03 2019-4-28
 */
public class GPageHelper {


    /**
     * <p>分页拦截器</p>
     * @param filter 基础过滤器/继承该类的子类都可以传
     */
    public static void pageIntercept(GFilter filter){

        Integer pageNum = filter.getPageNum();
        Integer pageSize = filter.getPageSize();
        if(!GeneralUtils.isNotEmpty(pageNum)){
            pageNum = 1;
        }
        if(!GeneralUtils.isNotEmpty(pageSize) || pageSize>500){
            pageSize = 100;
        }

        PageHelper.startPage(pageNum,pageSize);
    }

    /**
     * <p>通过业务模型集合和实体数据集合构建通用GxPage对象</p>
     * @param items 业务模型列表
     * @param entityPages 数据实体分页对象
     * @param <T> 业务模型泛型
     * @param <K> 数据实体泛型
     * @return GxPage
     */
    public static<T,K> PageResult<T> buildPage(List<T> items, Page<K> entityPages){
        PageResult<T> page = new PageResult<>();
        try{
            page.setItems(items);
            page.setPageItems((long) entityPages.getResult().size());
            page.setPageNo((long) entityPages.getPageNum());
            page.setPageSize((long) entityPages.getPageSize());
            page.setTotalPages((long) entityPages.getPages());
            page.setTotalItems(entityPages.getTotal());
        }catch (Exception ex){
            LoggerHelper.error("构建分页对象失败",ex);
        }
        return page;
    }
}
