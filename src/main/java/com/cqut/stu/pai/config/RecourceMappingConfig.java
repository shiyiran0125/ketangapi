package com.cqut.stu.pai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 石益然
 * @program: pai
 * @description: 静态资源映射
 * @date 2020-11-19 17:35:44
 */
@Configuration
public class RecourceMappingConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:D:/upload_new/");
    }
}