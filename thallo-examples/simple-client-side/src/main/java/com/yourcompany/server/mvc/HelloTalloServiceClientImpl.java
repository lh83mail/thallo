package com.yourcompany.server.mvc;

import com.netflix.discovery.EurekaClient;
import com.yourcompany.service.HelloThalloService;
import com.yourcompany.service.Thallo;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihong on 17-3-17.
 */
@RestController
public class HelloTalloServiceClientImpl  {
    private HelloThalloService helloThalloService;
    @Autowired
    private EurekaClient discoveryClient;

    @RequestMapping("/invoke")
    public Thallo sayHello(String name) {
        return helloThalloService.sayHello(name);
    }

    @Autowired
    public void setHelloThalloService(HelloThalloService helloThalloService) {
        this.helloThalloService = helloThalloService;
    }
}
