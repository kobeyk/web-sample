package com.appleyk.controller.test;

import com.appleyk.core.annotation.GToken;
import com.appleyk.core.common.RequestMappingConstant;
import com.appleyk.core.common.ResponseResult;
import com.appleyk.core.common.ex.CommonException;
import com.appleyk.core.common.ex.MyException;
import com.appleyk.core.dict.ResultCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>测试Controller</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 上午 10:43 2019-4-27
 */
@RestController
@RequestMapping(value = RequestMappingConstant.ROOT_MAPPING_VALUE)
public class HelloWorld {

    @GetMapping("/hello")
    public ResponseEntity hello(){
        Map<String,Object> mapResult = new HashMap<>();
        mapResult.put("author","appleyk" );
        mapResult.put("age","18" );
        return ResponseEntity.ok(ResponseResult.ok(mapResult));
    }

    @GetMapping("/excep")
    public ResponseEntity excepTest() throws Exception{
        throw new CommonException(ResultCode.OBJECT_NOT_EXIST,"目录对象不存在！");
    }

    @GetMapping("/excep/my")
    public ResponseEntity myExcepTest() throws MyException {
        throw new MyException("目录对象不存在！");
    }

    @GToken
    @GetMapping("/token")
    public ResponseEntity login() throws Exception{
        return ResponseEntity.ok(ResponseResult.ok("登录成功"));
    }

}
