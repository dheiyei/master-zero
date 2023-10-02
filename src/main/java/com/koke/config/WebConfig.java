package com.koke.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 地址映射
 * @author koke
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 静态地址映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/demo/**")
                // 映射本地静态资源
                .addResourceLocations("file:/D:/_A-Environment-IDEA/file/");
    }

}
