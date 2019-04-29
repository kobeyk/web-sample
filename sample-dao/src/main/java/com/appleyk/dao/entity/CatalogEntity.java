package com.appleyk.dao.entity;

import com.appleyk.core.model.Catalog;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>目录数据实体类</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 上午 11:43 2019-4-27
 */
// 关联表名，也可以实现表名的动态配置（注入一个value）
@Table(name = "yk_catalog")
public class CatalogEntity {

    @Id
    private Long id;

    private String name;

    @Column(name = "parentid")
    private Long parentId;

    @Column(name = "rootid")
    private Long rootId;

    @Column(name = "ctime")
    private Long cTime ;

    @Column(name = "mtime")
    private Long mTime;

    private CatalogEntity(){

    }

    private CatalogEntity(Catalog catalog){
        this.id = catalog.getId();
        this.name = catalog.getName();
        this.parentId = catalog.getParentId();
        this.cTime = catalog.getcTime();
        this.mTime = System.currentTimeMillis();
    }

    /**
     * 对象业务模型转数据实体类
     * @param catalog 目录业务模型
     * @return 目录数据实体类
     */
    public static CatalogEntity createEntity(Catalog catalog){
        return new CatalogEntity(catalog);
    }


    /**
     * 数据实体类转业务模型
     * @param catalogEntity 目录数据实体类
     * @return 目录业务模型对象
     */
    public static Catalog createModel(CatalogEntity catalogEntity){
        Catalog catalog = new Catalog();
        catalog.setId(catalogEntity.getId());
        catalog.setName(catalogEntity.getName());
        catalog.setParentId(catalogEntity.getParentId());
        catalog.setcTime(catalogEntity.getcTime());
        return catalog;
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

    public Long getRootId() {
        return rootId;
    }

    public void setRootId(Long rootId) {
        this.rootId = rootId;
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

    public Long getmTime() {
        return mTime;
    }

    public void setmTime(Long mTime) {
        this.mTime = mTime;
    }
}
