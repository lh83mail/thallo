package org.thallo.starter.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Configuration
@EnableOAuth2Client
public class Confi extends WebSecurityConfigurerAdapter {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(OAuth2ClientContext oauth2ClientContext){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                if (authentication.getClass().isAssignableFrom(JwtAuthenticationToken.class)) {
//                    JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
//                    request.getHeaders().setBearerAuth(
//                            jwtAuthenticationToken.getToken().getTokenValue()
//                    );
//                }

                return execution.execute(request, body);
            }
        }));
        return restTemplate;
    }

    private HttpServletRequest getHttpServletRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.httpBasic().disable();
        http.oauth2ResourceServer()
            .jwt();
    }

//    @Autowired
//    private RedisConnectionFactory connectionFactory;
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwkTokenStore();
//    }


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
