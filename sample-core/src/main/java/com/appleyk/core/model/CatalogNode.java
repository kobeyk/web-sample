package com.appleyk.core.model;

/**
 * <p>目录节点 -- 节点对象（文件）</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 上午 9:59 2019-4-27
 */
public class CatalogNode extends GObject {

    /**
     * 所属的目录 -- 目录ID，可以理解为节点的父目录ID
     */
    private Long catalogId;

    /**
     * 节点本身可以是一个对象
     */
    private GObject obj;

    public CatalogNode(){

    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public GObject getObj() {
        return obj;
    }

    public void setObj(GObject obj) {
        this.obj = obj;
    }

    /**
     * 设置节点对象
     * @param id 对象ID
     * @param name 对象名称
     */
    public void setObj(Long id , String name)
    {
        GObject gObject = new GObject();
        gObject.setId(id);
        gObject.setName(name);
        this.obj = gObject;
    }


    /**
     * 获取对象名称
     * @return
     */
    public String fetchObjName(){
        if(obj == null || obj.getName() == null){
            return "";
        }
        return obj.getName();
    }

    /**
     * 获取对象ID
     * @return
     */
    public Long fetchObjId(){
        if(obj == null || obj.getId() == null){
            return 0L;
        }
        return obj.getId();
    }

}
