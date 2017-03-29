package org.halo.thallo.server;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private String name = "World";

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