package com.yourcompany.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by lihong on 17-3-17.
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
@EnableHystrix
public class SimpleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleServerApplication.class, args);
    }
}
