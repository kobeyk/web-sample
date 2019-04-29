package com.appleyk.controller;

import com.appleyk.controller.base.BaseController;
import com.appleyk.core.common.RequestMappingConstant;
import com.appleyk.core.common.ResponseResult;
import com.appleyk.core.filter.GFilter;
import com.appleyk.core.model.Catalog;
import com.appleyk.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>目录接口请求处理器</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 4:26 2019-4-28
 */
@CrossOrigin
@RestController
@RequestMapping(value = RequestMappingConstant.ROOT_MAPPING_VALUE+"/catalog")
public class CatalogController extends BaseController {

    @Autowired
    private CatalogService catalogService;


    /**

     <p>创建目录测试JSON</p>

     {
         "name": "PostgreSql用户组",
         "parentId": 0
     }

     <p>响应结果</p>

     {
         "status": 200,
         "message": "成功",
         "data": {
             "id": 10000,
             "name": "PostgreSql用户组",
             "children": [],
             "parentId": 0,
             "cTime": 1556441792585
         },
         "timeStamp": "2019-04-28 16:56:32"
     }

     */

    /**
     * <p>创建目录，需指定目录的根节点，如果rootId和parentId均为0，则表明该目录是超级根</p>
     * @param catalog 目录业务模型
     * @param rootId 根目录ID
     * @return ResponseEntity
     */
    @PostMapping(RequestMappingConstant.CREATE_MAPPING_VALUE+"/{rootId}")
    public ResponseEntity create(@RequestBody Catalog catalog,
                                 @PathVariable("rootId") Long rootId) throws Exception{

        catalogService.create(catalog,rootId);
        return ResponseEntity.ok(ResponseResult.ok(catalog));

    }

    /**
     * <p>更新目录</p>
     * @param catalog 目录业务模型
     * @return ResponseEntity
     */
    @PostMapping(RequestMappingConstant.UPDATE_MAPPING_VALUE)
    public ResponseEntity update(@RequestBody Catalog catalog) throws Exception{

        checkId(catalog.getId());
        catalogService.update(catalog);
        return ResponseEntity.ok(ResponseResult.ok(catalog));

    }

    /**
     * <p>删除目录</p>
     * @param id 目录id
     * @return ResponseEntity
     */
    @DeleteMapping(RequestMappingConstant.DELETE_MAPPING_VALUE+"/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception{

        checkId(id);
        catalogService.delete(id);
        return ResponseEntity.ok(ResponseResult.ok("删除目录成功！"));

    }


    /**
     * <p>查询目录</p>
     * @param filter 目录业务模型
     * @return ResponseEntity
     */
    @GetMapping(RequestMappingConstant.QUERY_MAPPING_VALUE)
    public ResponseEntity query(GFilter filter) throws Exception{
        return ResponseEntity.ok(ResponseResult.ok(catalogService.query(filter)));
    }

    /**
     * <p>查询目录树的根</p>
     * @param filter 基础过滤器
     * @return ResponseEntity
     */
    @GetMapping("/root/"+RequestMappingConstant.QUERY_MAPPING_VALUE)
    public ResponseEntity queryRoot(GFilter filter) throws Exception{
        return ResponseEntity.ok(ResponseResult.ok(catalogService.queryRoots(filter)));
    }
}
