package io.github.lh83mail.thallo.gateway.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Configuration
public class GatewayConfiguration {
    @Bean
    public GlobalFilter customGlobalFilter() {
        return (exchange, chain) ->
                exchange.getPrincipal()
                .map(Principal::getName)
                .defaultIfEmpty("匿名用户")
                .map(userName -> {
                    System.out.println("LLLLLLLLLLLLLLLLL" +  userName);
                    System.out.println("LLLLLLLLLLLLLLLLL:" + exchange.getRequest().getHeaders().getFirst("Authorization"));
                    //adds header to proxied request
                    exchange.getRequest().mutate().header("CUSTOM-REQUEST-HEADER", userName).build();
                    return exchange;
                })
                .flatMap(chain::filter);
    }

    @Bean
    public GlobalFilter customGlobalPostFilter() {
        return (exchange, chain) -> chain.filter(exchange)
                .then(Mono.just(exchange))
                .map(serverWebExchange -> {
                    //adds header to response
                    serverWebExchange.getResponse().getHeaders().set("CUSTOM-RESPONSE-HEADER",
                            HttpStatus.OK.equals(serverWebExchange.getResponse().getStatusCode()) ? "It worked": "It did not work");
                    return serverWebExchange;
                })
                .then();
    }
}
