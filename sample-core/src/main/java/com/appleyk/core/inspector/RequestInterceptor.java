package com.appleyk.core.inspector;

import com.appleyk.core.annotation.GToken;
import com.appleyk.core.common.ex.CommonException;
import com.appleyk.core.dict.ResultCode;
import com.appleyk.core.helper.UserHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * <p>请求拦截器 -- 主要用于验证请求的用户token是否有效</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 3:25 2019-4-27
 */
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 如果不是映射到方法直接通过
        if(!(object instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)object;
        Method method=handlerMethod.getMethod();
        //检查是否有token注解，有则验证token的合法性
        if (method.isAnnotationPresent(GToken.class)) {

            // 取request请求中的header头字段信息
            String token = httpServletRequest.getHeader("token");
            if(StringUtils.isBlank(token)){
                throw new CommonException(ResultCode.INVALID_GRANT,"用户令牌不允许空！");
            }

            // 验证token
            boolean b = UserHelper.checkToken(token);
            if(!b){
                throw new CommonException(ResultCode.INVALID_CLIENT,"用户令牌认证失败！");
            }

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
