package org.thallo.gatewayserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
//@EnableWebFluxSecurity
public class GatewayConfiguration  {

//    @Autowired
//    private DiscoveryClient discoveryClient;
//    @Autowired
//    ApplicationContext context;
//
//
//    @Bean
//    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
//        System.out.println("::::::::::::::::::" + discoveryClient);
//
//        http.csrf().disable();
//        http.oauth2ResourceServer().jwt()
//                .jwkSetUri("http://localhost:8084/.well-known/jwks");
//        http.authorizeExchange()
//             .pathMatchers("/authz-server/**").permitAll()
//             .anyExchange()
//                .authenticated();
//
//        SecurityWebFilterChain result = http.build();
//        return result;
//
//    }

}
