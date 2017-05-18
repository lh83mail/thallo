package org.halo.thallo.gatewayserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lihong on 17-4-13.
 */
@Configuration
public class ViewConfiguration extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/login").setViewName("login");
            registry.addViewController("/oauth/confirm_access").setViewName("authorize");
        }

//    @Configuration
//    @Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
//    protected static class LoginConfig extends WebSecurityConfigurerAdapter {
//
//        @Autowired
//        private AuthenticationManager authenticationManager;
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
//                    .anyRequest().authenticated();
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.parentAuthenticationManager(authenticationManager);
//        }
//    }

}
