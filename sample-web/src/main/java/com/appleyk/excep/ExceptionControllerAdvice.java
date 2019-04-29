package com.appleyk.excep;

import com.appleyk.core.common.ResponseResult;
import com.appleyk.core.common.ex.CommonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * <p>拦截控制器Controller异常，并进行统一处理</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 上午 11:05 2019-4-27
 */
@ControllerAdvice
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }

    @ModelAttribute
    public void addAttributes(Model model) {
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity errorHandler(Exception ex) {
        if (ex instanceof CommonException) {
            CommonException commonException = (CommonException) ex;
            return ResponseEntity.ok(commonException.buildResult());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseResult.fail(ex.getMessage()));
    }
}

