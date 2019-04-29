package com.appleyk.config;

import com.appleyk.core.inspector.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>配置请求拦截器</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 下午 3:24 2019-4-27
 */
@Configuration
public class RequestInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，判断参数是否合法
        registry.addInterceptor(requestInterceptor()).addPathPatterns("/**");
    }
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor();
    }

}
