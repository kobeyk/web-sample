package com.appleyk.core.helper;

import com.appleyk.core.filter.GFilter;
import com.appleyk.core.utils.GeneralUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p>基础过滤器帮助类</p>
 *
 * @author yukun24@126.com
 * @version V.1.0.1
 * @company 苏州中科蓝迪
 * @date created on 上午 9:58 2019-4-26
 */
public class GxFilterHelper {

    /**
     * 对象id
     */
    private List<String> ids = new ArrayList<>();

    private Long id;
    /**
     * 对象名称
     */
    private List<String> names = new ArrayList<>();


    public static Example buildQueryExample(GFilter filter , Class classz){

        Example example = new Example(classz);

        Long id = filter.getId();
        Set<Long> ids = filter.getIds();
        String name = filter.getName();

        boolean idNotEmpty = GeneralUtils.isNotEmpty(id) || (GeneralUtils.isNotEmpty(id) && GeneralUtils.isNotEmpty(ids));
        boolean idsNotEmpty = GeneralUtils.isNotEmpty(ids);
        boolean nameNotEmpty = GeneralUtils.isNotEmpty(name);

        /**
         * 1、如果id 和 ids 同时存在，优先级，按id查
         * 2、如果ids和name同时存在，按name查
          */
        if(idNotEmpty){
            example.and().andEqualTo("id",id );
        }

        if(nameNotEmpty){
            example.and().andLike("name","%"+name+"%");
        }

        if(idsNotEmpty && nameNotEmpty){
            example.and().andLike("name","%"+name+"%");
        }

        if(!idNotEmpty && idsNotEmpty){
            example.and().andIn("id",ids);
        }

        return example;
    }

}
