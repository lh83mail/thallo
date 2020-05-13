package io.github.lh83mail.thallo.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.config.GlobalCorsProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.util.List;


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
        //FIX 全局CORS配置对于Webflux不生效，导致 OPTIONS /path 产生HTTP CODE 403
        globalCorsProperties.getCorsConfigurations().forEach((k,v) -> {
            registry.addMapping(k)
                .allowedOrigins(listToArray(v.getAllowedOrigins()))
                .allowedHeaders(listToArray(v.getAllowedHeaders()))
                .allowedMethods(listToArray(v.getAllowedMethods()))
                .allowCredentials(v.getAllowCredentials() == null ? false : v.getAllowCredentials())
                .exposedHeaders(listToArray(v.getExposedHeaders()))
                .maxAge(v.getMaxAge() == null ?  1800L : v.getMaxAge());
        });
    }


    private String [] listToArray(List<String> list) {
        if (list == null) {
            return EMPTY_STRING_ARRAY;
        }
        return list.toArray(new String[list.size()]);
    }

    private final static String[] EMPTY_STRING_ARRAY = new String[]{};
}
