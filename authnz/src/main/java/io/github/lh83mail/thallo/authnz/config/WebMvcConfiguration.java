package io.github.lh83mail.thallo.authnz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web基本配置
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 登录页面
        registry.addViewController(securityProperties.getLoginUrl())
                .setViewName(securityProperties.getLoginPage());

        // 登录成功页面
        registry.addViewController(securityProperties.getLoginSuccessUrl())
                    .setViewName(securityProperties.getLoginSuccessPage());
        
    }
}
