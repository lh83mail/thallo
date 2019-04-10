package org.halo.thallo.registryserver;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by lihong on 17-3-13.
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryServer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RegistryServer.class)
                .run(args);
    }
}