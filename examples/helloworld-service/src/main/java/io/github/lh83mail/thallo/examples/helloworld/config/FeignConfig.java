package io.github.lh83mail.thallo.examples.helloworld.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Configuration
public class FeignConfig  {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            //添加token
            Optional.ofNullable(request.getHeader("Authorization"))
                    .ifPresent( value -> {
                        template.header("Authorization", value);
                    });

        };
    }
}