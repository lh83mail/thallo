package org.thallo.authnz;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.halo.thallo.common.repository.CommonJpaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableMethodCache(basePackages = "org.halo.thallo")
@EnableCreateCacheAnnotation
@EntityScan(basePackages = {"org.thallo.authnz.**.entity"})
@EnableJpaRepositories(basePackages = "org.thallo.authnz", repositoryBaseClass = CommonJpaRepository.class)
@EnableTransactionManagement
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class AuthnzServer {
    public static void main(String[] args) {
        SpringApplication.run(AuthnzServer.class, args);
    }
}
