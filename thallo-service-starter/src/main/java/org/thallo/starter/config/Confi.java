package org.thallo.starter.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
//@EnableResourceServer
public class Confi {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }


//    private final ResourceServerProperties resource;
//
//
//    @Bean
//    public RemoteTokenServices remoteTokenServices() {
//        RemoteTokenServices services = new RemoteTokenServices();
//        services.setCheckTokenEndpointUrl(this.resource.getTokenInfoUri());
//        services.setClientId(this.resource.getClientId());
//        services.setClientSecret(this.resource.getClientSecret());
//        return services;
//    }


//    @Bean
//    UserAuthenticationConverter accessTokenConverter() {
//        DefaultUserAuthenticationConverter converter = new DefaultUserAuthenticationConverter();
//        return converter;
//    }
//
//    @Configuration
//    @Order(101)
//
//    protected static class LoginConfig extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            super.configure(http);
////            http
////                    .oauth2Login()
////                    .and()
////                    .oauth2Client()
//
////            .and()
////                    .oauth2ResourceServer().jwt().jwkSetUri("http://localhost:8084/oauth/token_keys");
//
////            http.formLogin().loginPage("/login")
////                    .permitAll()
////                    .successForwardUrl("/")
////                    .and()
////                    .authorizeRequests()
////                    .anyRequest()
////                    .authenticated()
////                    .and()
////                    .logout()
////                    .and().oauth2Client()
////                    .and().csrf().ignoringAntMatchers("/**");
//
//        }
//
//
//    }
//
//    @Configuration
//    @EnableResourceServer
//    @EnableOAuth2Client
//    protected static class OauthServer extends ResourceServerConfigurerAdapter {
//
////        @Autowired
////        private ResourceServerProperties resource;
////
////        @Override
////        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
////            super.configure(resources);
////        }
//    }

}
