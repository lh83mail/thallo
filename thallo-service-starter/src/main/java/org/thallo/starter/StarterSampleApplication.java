package org.thallo.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@SpringBootApplication
@EnableDiscoveryClient
@RestController
//@EnableResourceServer
public class StarterSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterSampleApplication.class, args);
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/userinfo")
    public Map defaultM() {
        HashMap<String, Object> map = new HashMap<>();
            map.put("my-name", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            map.put("name","hello2");
            map.put("email","email2");

        Map result = restTemplate.getForObject("http://example-server/sayHello?name=GOS", Map.class);
            map.put("sayHello", result);
            return map;
    }
}
