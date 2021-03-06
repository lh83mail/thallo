package io.github.lh83mail.thallo.authnz.config;

import io.github.lh83mail.thallo.authnz.authen.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

import static io.github.lh83mail.thallo.authnz.config.Oauth2ServerConfiguration.WELL_KNOWN_JWKS_JSON_URI;

@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true);
        super.configure(web);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        // TODO add customize authentication provider
//        auth.authenticationProvider(new XTokenAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // session management
        http.sessionManagement()
                .maximumSessions(securityProperties.getMaximumSessions());

        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers(
                    securityProperties.getLoginPage(),
                    securityProperties.getLoginFailureUrl(),
                    securityProperties.getLoginUrl(),
                    WELL_KNOWN_JWKS_JSON_URI
                ).permitAll()
                .anyRequest().authenticated()
                // TODO Add Customize Filters
//                .and()
//                    .addFilterBefore(new VerifyXTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .and()
                    .exceptionHandling()
                    .accessDeniedPage(securityProperties.getAccessDeniedUrl())
                .and()
                .csrf()
                    .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
                .logout()
                    .logoutUrl(securityProperties.getLogoutUrl())
                    .logoutSuccessUrl(securityProperties.getLogoutSuccessPage())
                .and()
                .csrf()
                .and()
                .formLogin()
                    .loginPage(securityProperties.getLoginUrl())
                    .loginProcessingUrl(securityProperties.getLoginProcessUrl())
                    .defaultSuccessUrl(securityProperties.getLoginSuccessUrl())
                    .failureUrl(securityProperties.getLoginFailureUrl())
                .and()
                    .rememberMe()
                ;
    }


    @Configuration
    protected static class UserInfoConfiguration {
        @Bean
        public UserDetailsService jdbcUserDetailService(DataSource dataSource) {
            return new UserDetailServiceImpl(dataSource);
        }
    }
}
