package com.appleyk.core.filter;

import java.util.LinkedHashSet;

/**
 * <p>基础过滤器</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:24 2019-4-28
 */
public class GFilter {

    private Long id;
    private String name;
    private LinkedHashSet<Long> ids;
    private Integer pageNum = 1;
    private Integer pageSize = 200;

    public GFilter(){

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

    public LinkedHashSet<Long> getIds() {
        return ids;
    }

    public void setIds(LinkedHashSet<Long> ids) {
        this.ids = ids;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
