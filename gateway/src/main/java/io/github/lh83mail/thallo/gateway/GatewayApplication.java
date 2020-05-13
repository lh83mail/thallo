package io.github.lh83mail.thallo.gateway;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import io.github.lh83mail.thallo.common.repository.CommonJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Create At  2020/1/6 13:20
 *
 * @author: Lh
 * @version: 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableMethodCache(basePackages = "org.halo.thallo")
@EnableCreateCacheAnnotation
@EntityScan(basePackages = {"org.halo.thallo.gateway.**.entity"})
@EnableJpaRepositories(basePackages = "org.halo.thallo.gateway", repositoryBaseClass = CommonJpaRepository.class)
@EnableTransactionManagement
//@EnableWebFluxSecurity
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
