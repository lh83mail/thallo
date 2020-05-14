package io.github.lh83mail.thallo.authnz.config;

import io.github.lh83mail.thallo.authnz.authen.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * OAuth2AccessToken 令牌存储库
     */
    @Bean
    @ConditionalOnBean(RedisConnectionFactory.class)
    public TokenStore redisTokenStore(RedisConnectionFactory connectionFactory) {
        return new RedisTokenStore(connectionFactory);
    }


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
        // @formatter:off
        http
                .authorizeRequests()
                .antMatchers(
                    securityProperties.getLoginPage(),
                    securityProperties.getLoginFailureUrl(),
                    securityProperties.getLoginUrl(),
                    "/.well-known/jwks"
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
