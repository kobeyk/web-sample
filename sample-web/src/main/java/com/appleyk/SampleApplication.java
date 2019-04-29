package com.appleyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * <p>项目应用（App）启动入口</p>
 *
 * @author appleyk
 * @version V.1.0.0
 * @blob https://blog.csdn.net/appleyk
 * @date created on 上午 10:32 2019-4-27
 */
@SpringBootApplication
public class SampleApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(com.appleyk.SampleApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(com.appleyk.SampleApplication.class);
    }
}

