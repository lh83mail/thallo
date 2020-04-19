package org.halo.thallo.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.stream.Collectors;


/**
 * Create At  2020/1/7 16:21
 *
 * @author: Lh
 * @version: 1.0.0
 */
@Configuration
public class WebMvcConfig implements WebFluxConfigurer {
    @Autowired
    private GlobalCorsProperties globalCorsProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        globalCorsProperties.getCorsConfigurations().forEach((k,v) -> {
            registry.addMapping(k)
            .allowedOrigins(v.getAllowedOrigins().toArray(new String[v.getAllowedOrigins().size()]));
        });
    }

}
