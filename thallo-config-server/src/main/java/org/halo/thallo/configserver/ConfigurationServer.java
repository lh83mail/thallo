package org.halo.thallo.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Created by lihong on 17-3-13.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigurationServer {
    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServer.class, args);
    }
}