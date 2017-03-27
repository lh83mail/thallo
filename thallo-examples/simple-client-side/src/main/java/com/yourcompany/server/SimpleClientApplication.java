package com.yourcompany.server;

import com.yourcompany.service.HelloThalloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lihong on 17-3-17.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@Configuration
@ComponentScan(basePackages = {"com.yourcompany", "com.yourcompany.service"})
public class SimpleClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleClientApplication.class, args);
    }

    @FeignClient("server1")
    public interface InterFace extends HelloThalloService {}
}
