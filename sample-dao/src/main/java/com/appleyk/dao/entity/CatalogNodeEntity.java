package com.appleyk.dao.entity;

import com.appleyk.core.model.CatalogNode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>目录节点数据实体</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 1:25 2019-4-27
 */
// 关联表名，也可以实现表名的动态配置（注入一个value）
@Table(name = "yk_catalog_node")
public class CatalogNodeEntity {

    @Id
    private Long id;

    private String name;

    /**
     * 属性字段和表字段对应
     */
    @Column(name = "oid")
    private Long oId;

    @Column(name = "catalogid")
    private Long catalogId;

    @Column(name = "ctime")
    private Long cTime = System.currentTimeMillis();

    @Column(name = "mtime")
    private Long mTime;

    private CatalogNodeEntity(){

    }

    /**
     * 私有构造器，防止外部直接new
     * @param catalogNode 目录节点业务模型
     */
    private CatalogNodeEntity(CatalogNode catalogNode){
        this.id = catalogNode.getId();
        this.name =catalogNode.fetchObjName();
        this.oId = catalogNode.fetchObjId();
        this.catalogId = catalogNode.getCatalogId();
    }

    /**
     * 根据业务模型创建目录节点数据实体对象
     * @param catalogNode 目录节点业务模型
     * @return 目录节点数据实体对象
     */
    public static CatalogNodeEntity createEntity(CatalogNode catalogNode){
        return new CatalogNodeEntity(catalogNode);
    }

    /**
     * 基于所属目录，创建目录节点
     * @param catalogNode 目录节点业务模型
     * @param catalogId 目录ID
     * @return 目录节点数据实体对象
     */
    public static CatalogNodeEntity createEntity(CatalogNode catalogNode,Long catalogId){
        CatalogNodeEntity catalogNodeEntity = new CatalogNodeEntity(catalogNode);
        catalogNodeEntity.setCatalogId(catalogId);
        return catalogNodeEntity;
    }

    /**
     * 根据目录节点数据实体对象创建业务模型
     * @param nodeEntity 目录节点数据实体对象
     * @return 目录节点业务模型
     */
    public static CatalogNode createModel(CatalogNodeEntity nodeEntity){
        CatalogNode catalogNode = new CatalogNode();
        catalogNode.setId(nodeEntity.getId());
        catalogNode.setObj(nodeEntity.getId(),nodeEntity.getName());
        return catalogNode;
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

    public Long getoId() {
        return oId;
    }

    public void setoId(Long oId) {
        this.oId = oId;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public Long getcTime() {
        return cTime;
    }

    public void setcTime(Long cTime) {
        this.cTime = cTime;
    }

    public Long getmTime() {
        return mTime;
    }

    public void setmTime(Long mTime) {
        this.mTime = mTime;
    }
}

