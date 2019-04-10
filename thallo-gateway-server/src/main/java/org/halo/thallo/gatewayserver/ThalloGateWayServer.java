package org.halo.thallo.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by lihong on 17-3-29.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ThalloGateWayServer {
    public static void main(String[] args) {
        SpringApplication.run(ThalloGateWayServer.class, args);
    }
}
