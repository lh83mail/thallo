package org.thallo.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;

@Configuration
//@EnableResourceServer
public class Confi {

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
