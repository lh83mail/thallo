package org.halo.thallo.api.docs.swagger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient
public interface SwaggerResourceClient {
    /**
     * swagger2默认的url后缀
     */
    String SWAGGER2URL = "/v2/api-docs";
    
    @GetMapping(SWAGGER2URL)
    String getApiDocs();
}
