package com.appleyk.dao.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * <p>自增ID序列值提取</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 3:12 2019-4-28
 */
@Service
public interface IdMapper {

    /**
     * 获取全局对象ID
     * @return ID
     */
    @Select("SELECT nextval('yk_object_id')")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    Long getObjectId();
}
