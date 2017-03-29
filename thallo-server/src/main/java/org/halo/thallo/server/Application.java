package org.halo.thallo.server;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lihong on 17-3-13.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@Configuration
@RestController
public class Application {
    @Value("${config.name}")
    String name = "World";

    @Autowired
    private EurekaClient discoveryClient;

    @RequestMapping("/")
    public String home() {
        Applications instance =discoveryClient.getApplications();
        return "Hello ()" + name;
    }

//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
}