package org.thallo.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thallo.starter.api.HelloThalloService;
import org.thallo.starter.api.Thallo;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableCircuitBreaker
@EnableFeignClients(basePackages = "org.thallo.starter")
@LoadBalancerClients
public class StarterSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterSampleApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloThalloService helloThalloService;

    @RequestMapping("/userinfo")
    public Map defaultM(Principal principal, HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
            map.put("my-name", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            map.put("name","hello2");
            map.put("email","email2");
            map.put("principal", principal);

            Thallo x = helloThalloService.sayHello("GOX");

//            Map result = restTemplate.getForObject("http://example-server/sayHello?name=GOS", Map.class);
            map.put("sayHello", x);
            return map;
    }
}
