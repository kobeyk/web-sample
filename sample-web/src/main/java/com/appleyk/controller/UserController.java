package com.appleyk.controller;

import com.appleyk.controller.base.BaseController;
import com.appleyk.core.common.RequestMappingConstant;
import com.appleyk.core.common.ResponseResult;
import com.appleyk.core.filter.GFilter;
import com.appleyk.core.model.GUser;
import com.appleyk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>用户接口请求处理器</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 5:34 2019-4-28
 */
@CrossOrigin
@RestController
@RequestMapping(value = RequestMappingConstant.ROOT_MAPPING_VALUE+"/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    /**

     <p>创建用户测试JSON</p>

     {
        "name":"appleyk",
        "avatar":"http://xxx.tx.jpg",
        "telPhone":"123456789",
        "role":0
     }

     <p>响应结果</p>

     {
         "status": 200,
         "message": "成功",
         "data": {
             "id": 10004,
             "name": "appleyk",
             "avatar": "http://xxx.tx.jpg",
             "telPhone": "123456789",
             "role": 0,
             "cTime": "2019-04-29 12:42:49"
         },
         "timeStamp": "2019-04-29 12:42:49"
     }

     */

    /**
     * <p>创建用户</p>
     * @param gUser 目录业务模型
     * @return ResponseEntity
     */
    @PostMapping(RequestMappingConstant.CREATE_MAPPING_VALUE)
    public ResponseEntity create(@RequestBody GUser gUser) throws Exception{

        checkName(gUser);
        userService.create(gUser);
        return ResponseEntity.ok(ResponseResult.ok(gUser));

    }

    /**
     * <p>更新用户</p>
     * @param gUser 用户业务模型
     * @return ResponseEntity
     */
    @PostMapping(RequestMappingConstant.UPDATE_MAPPING_VALUE)
    public ResponseEntity update(@RequestBody GUser gUser) throws Exception{

        checkId(gUser);
        userService.update(gUser);
        return ResponseEntity.ok(ResponseResult.ok(gUser));

    }

    /**
     * <p>删除用户 -- 判断删除的用户是否是超级管理员，是则无法删除</p>
     * <p>这一块逻辑有点问题，是出于简单考虑的</p>
     * <p>如果想复杂，可以加注解，配合拦截器，对用户token进行验证，验证token如果是超管，则可以进行用户的删除操作</p>
     * <p>本次删除，只是判断删除的用户是不是超级管理员，业务简单</p>
     * @param id 用户id
     * @return ResponseEntity
     */
    @DeleteMapping(RequestMappingConstant.DELETE_MAPPING_VALUE+"/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception{

        checkId(id);
        userService.delete(id);
        return ResponseEntity.ok(ResponseResult.ok("删除用户成功！"));

    }


    /**
     * <p>查询用户 -- 普通用户不具备查的</p>
     * @param filter 查询过滤器
     * @return ResponseEntity
     */
    @GetMapping(RequestMappingConstant.QUERY_MAPPING_VALUE)
    public ResponseEntity query(GFilter filter) throws Exception{
        return ResponseEntity.ok(ResponseResult.ok(userService.query(filter)));
    }

}
