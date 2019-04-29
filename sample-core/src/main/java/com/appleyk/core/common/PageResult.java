package com.appleyk.core.common;

import java.util.List;

/**
 * <p>分页结果</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 2:21 2019-4-28
 */
public class PageResult<T> {

    /**
     * 实体总数量
     */
    private Long totalItems;

    /**
     * 总页数
     */
    private Long totalPages;

    /**
     * 当前页码（从1开始计数）
     */
    private Long pageNo;

    /**
     * 页面最大容量
     */
    private Long pageSize;

    /**
     * 当前页面实体数量
     */
    private Long pageItems;

    /**
     * 实体列表
     */
    private List<T> items;

    public PageResult(){

    }

    public PageResult(List<T> items) {
        this.items = items;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Long totalItems) {
        this.totalItems = totalItems;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getPageNo() {
        return pageNo;
    }

    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageItems() {
        return pageItems;
    }

    public void setPageItems(Long pageItems) {
        this.pageItems = pageItems;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

}
