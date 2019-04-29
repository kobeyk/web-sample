package com.appleyk.dao.mapper.catalog;

import com.appleyk.dao.entity.CatalogEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>目录通用Mapper接口，实现目录数据实体的增删改查</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 上午 11:52 2019-4-27
 */
public interface CatalogMapper extends Mapper<CatalogEntity> {

    /**
     * <p>更新目录数据 -- 方法名 和 xml映射文件里面对应的操作块的id保持一致</p>
     * @param catalogEntity 数据实体类
     * @return 影响的行数，成功 > 0,反之 = 0
     */
    Integer updateCatalog(@Param("catalog") CatalogEntity catalogEntity);

}
