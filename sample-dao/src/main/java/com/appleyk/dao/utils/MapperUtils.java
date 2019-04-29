package com.appleyk.dao.utils;

import com.appleyk.core.utils.GeneralUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * <p>通用Mapper工具类</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 上午 10:26 2019-4-29
 */
public class MapperUtils<T> {

    /**
     * <p>判断id是否存在 == 用于更新和删除</p>
     * @param mMapper 通用tk.mapper的实例
     * @param eClass  数据实体类
     * @param fieldName id对应的字段名
     * @param id 对象ID
     * @return 存在 true ，不存在 false
     */
    public static boolean objectIsExistById(Mapper mMapper , Class eClass,String fieldName, Long id){
        Example example =andEqualTo(eClass,fieldName ,id);
        int i = mMapper.selectCountByExample(example);
        return i>0;
    }

    /**
     * <p>判断id是否存在 == 用于删除,如果存在直接返回对应的entity，然后mapper直接删</p>
     * @param mMapper 通用tk.mapper的实例
     * @param eClass  数据实体类
     * @param id 对象ID
     * @param isReturn 如果存在了，是否返回对应的数据实体对象
     * @return 存在 true ，不存在 false
     */
    public static<T> T objectIsExistById(Mapper<T> mMapper , Class eClass, Long id,boolean isReturn){

        if(!isReturn){
            return null;
        }

        Example example = andEqualTo(eClass,"id" ,id);
        List<T> entities = mMapper.selectByExample(example);
        if(GeneralUtils.isEmpty(entities)){
            return null;
        }
        return entities.get(0);
    }


    /**
     * <p>判断名称是否存在 == 用于创建和更新时，名称不能重复</p>
     * <p>将数据库层面的字段约束，提到代码层面上，实现自主可控</p>
     * @param mMapper 通用tk.mapper的实例
     * @param eClass  数据实体类
     * @param name 对象名称
     * @return 存在 true ，不存在 false
     */
    public static boolean nameIsExist(Mapper mMapper , Class eClass, String name){
        Example example = andEqualTo(eClass,"name" ,name );
        int i = mMapper.selectCountByExample(example);
        return i>0;
    }

    /**
     * <p>根据字段名和值构建and、equalTo的条件对象Example</p>
     * @param eClass 实体类
     * @param fieldName 字段名
     * @param value 字段值
     * @return Example
     */
    private static Example andEqualTo(Class eClass, String fieldName , Object value){
        Example example = new Example(eClass);
        example.and().andEqualTo(fieldName,value);
        return example;
    }

}
