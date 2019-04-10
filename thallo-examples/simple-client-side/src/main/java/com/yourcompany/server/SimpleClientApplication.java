package com.yourcompany.server;

import com.yourcompany.service.HelloThalloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by lihong on 17-3-17.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.yourcompany", "com.yourcompany.service"})
public class SimpleClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleClientApplication.class, args);
    }

    @FeignClient("example-server")
    public interface InterFace extends HelloThalloService {}
}
