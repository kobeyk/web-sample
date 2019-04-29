package com.appleyk.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>目录 -- 业务模型（文件夹）</p>
 *
 * @author yukun24@126.com
 * @version V.1.0.1
 * @company 苏州中科蓝迪
 * @date created on 上午 8:57 2019-4-27
 */
public class Catalog extends GObject {

    /**
     * 目录子节点 -- 目录下面的目录
     */
    private List<Catalog> children;

    /**
     * 父目录ID -- 如果目录是超级根（目录树的最顶端），则parentId = 0
     */
    private Long parentId;

    /**
     * 创建时间 -- 默认系统当前时间
     */
    private Long cTime = System.currentTimeMillis();

    /**
     * 目录节点列表（目录下面的文件对象集合）
     */
    private List<CatalogNode> catalogNodes;

    public Catalog(){
        children = new ArrayList<>();
    }

    public List<Catalog> getChildren() {
        return children;
    }

    public void setChildren(List<Catalog> children) {
        this.children = children;
        this.catalogNodes = new ArrayList<>();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getcTime() {
        return cTime;
    }

    public void setcTime(Long cTime) {
        this.cTime = cTime;
    }

    public List<CatalogNode> getCatalogNodes() {
        return catalogNodes;
    }

    public void setCatalogNodes(List<CatalogNode> catalogNodes) {
        this.catalogNodes = catalogNodes;
    }
}
