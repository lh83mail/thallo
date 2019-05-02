package org.thallo.authenserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihong on 17-3-28.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(securedEnabled = true)
@RestController
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }

    @RequestMapping("/")
    public String defaultM() {
        return "Hello, ok";
    }
}
