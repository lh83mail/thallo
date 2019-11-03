package org.thallo.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by lihong on 17-3-29.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient

@EnableFeignClients
@EnableCircuitBreaker
//@EnableFeignClients(basePackages = "com.springboot.cloud.auth.client")
//@EnableMethodCache(basePackages = "com.springboot.cloud")
//@EnableCreateCacheAnnotation

public class ThalloGateWayServer {
    public static void main(String[] args) {
        SpringApplication.run(ThalloGateWayServer.class, args);
    }
}
