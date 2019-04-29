package com.appleyk.controller;

import com.appleyk.controller.base.BaseController;
import com.appleyk.core.common.RequestMappingConstant;
import com.appleyk.core.common.ResponseResult;
import com.appleyk.core.model.CatalogNode;
import com.appleyk.service.CatalogNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>目录节点控制器</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 12:41 2019-4-29
 */
@CrossOrigin
@RestController
@RequestMapping(value = RequestMappingConstant.ROOT_MAPPING_VALUE+"/catalog/node")
public class CatalogNodeController extends BaseController {


    @Autowired
    private CatalogNodeService nodeService;


    /**

     <p>创建目录节点测试JSON</p>

     {
         "catalogId":10003,
         "obj": {
             "id": 10004,
             "name": "appleyk"
         }
     }

     <p>响应结果</p>

     {
     "status": 200,
     "message": "成功",
     "data": {
         "id": 10001,
         "catalogId": 10003,
         "obj": {
             "id": 10004,
             "name": "appleyk"
         }
     },
     "timeStamp": "2019-04-29 14:02:19"
     }

     */

    /**
     * <p>创建目录节点 -- 即文件；在面向对象的语言里，文件就是一个对象</p>
     * @param catalogNode 目录节点业务模型
     * @return ResponseEntity
     */
    @PostMapping(RequestMappingConstant.CREATE_MAPPING_VALUE)
    public ResponseEntity create(@RequestBody CatalogNode catalogNode) throws Exception{

        nodeService.create(catalogNode);
        return ResponseEntity.ok(ResponseResult.ok(catalogNode));

    }

    /**
     * <p>更新目录节点</p>
     * @param catalogNode 目录节点业务模型
     * @return ResponseEntity
     */
    @PostMapping(RequestMappingConstant.UPDATE_MAPPING_VALUE)
    public ResponseEntity update(@RequestBody CatalogNode catalogNode) throws Exception{

        checkId(catalogNode.getCatalogId());
        nodeService.update(catalogNode);
        return ResponseEntity.ok(ResponseResult.ok(catalogNode));

    }

    /**
     * <p>删除目录节点</p>
     * @param id 目录节点id
     * @return ResponseEntity
     */
    @DeleteMapping(RequestMappingConstant.DELETE_MAPPING_VALUE+"/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception{

        checkId(id);
        nodeService.delete(id);
        return ResponseEntity.ok(ResponseResult.ok("删除目录节点成功！"));

    }


}
