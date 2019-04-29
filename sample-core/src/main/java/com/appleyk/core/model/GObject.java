package com.appleyk.core.model;

/**
 * <p>共有属性对象类 -- 如所有对象都是有id和name的 </p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 上午 9:00 2019-4-27
 */
public  class GObject {

    /**
     * 对象ID -- 唯一标识
     */
    private Long id;

    /**
     * 对象名称
     */
    private String name;

    public GObject(){

    }

    public GObject(Long id, String name) {
        this.id = id;
        this.name = name;
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

}
