package org.halo.thallo.gateway.config;

import org.halo.thallo.common.web.interceptors.UserContextInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Create At  2020/1/7 16:21
 *
 * @author: Lh
 * @version: 1.0.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public HandlerInterceptor userInterceptor() {
        return new UserContextInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor());
    }
}
