package com.yourcompany.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lihong on 17-3-17.
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableCircuitBreaker
@Configuration
public class SimpleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleServerApplication.class, args);
    }
}
