/*
 * Copyright (c) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.thallo.authz.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thallo.authz.server.authen.UserDetailServiceImpl;
import org.thallo.authz.server.oauth2.DelegateClientDetailService;
import org.thallo.authz.server.oauth2.ThalloAuthzServerOauth2Properties;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.HashSet;
import java.util.Set;

/**
 * OAuth2 服务器配置
 * @author lihong
 * @version 1.0.0
 * @date 2018-12-24 10:34
 */
@Configuration
@SessionAttributes("authorizationRequest")
@EnableWebSecurity
public class OAuth2Configuration implements WebMvcConfigurer {

    /**
     * 配置登录视图
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("login");
        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
    }

    /**
     * Web 安全配置
     */
    @Configuration
    protected static class LoginConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.formLogin().loginPage("/login")
                    .permitAll()
                    .successForwardUrl("/")
                    .and()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .logout()
                    .and().csrf().ignoringAntMatchers("/**");

        }

        @Bean
        public UserDetailsService jdbcUserDetailService(DataSource dataSource) {
            return new UserDetailServiceImpl(dataSource);
        }

    }

    /**
     * OAuth 数据服务配置
     */
    @Configuration
    @EnableAuthorizationServer
    @EnableConfigurationProperties(ThalloAuthzServerOauth2Properties.class)
    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private UserDetailsService userDetailsService;
        @Autowired
        private RedisConnectionFactory connectionFactory;
        @Autowired
        private DataSource dataSource;

        @Autowired
        private ThalloAuthzServerOauth2Properties thalloAuthzServerOauth2Properites;


        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            KeyPair keyPair = new KeyStoreKeyFactory(
                    new ClassPathResource("keystore.jks"), "foobar".toCharArray())
                    .getKeyPair("test");
            converter.setKeyPair(keyPair);
            return converter;
        }


        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            Set<ClientDetailsService> detailsServiceSet = new HashSet<>();

            ThalloAuthzServerOauth2Properties.Clients configuredClients = thalloAuthzServerOauth2Properites.getClients();

            // build In Memory Clients
            boolean enabledMemoryClient = configuredClients != null
                    && configuredClients.getMemoryClientsEnabled() != null && configuredClients.getMemoryClientsEnabled();

            if (enabledMemoryClient
                && configuredClients.getClientDetails() != null && !configuredClients.getClientDetails().isEmpty()
            ) {
                InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
                configuredClients.getClientDetails().forEach(i -> {
                    ClientDetailsServiceBuilder.ClientBuilder clientBuilder = builder.withClient(i.getClientId())
                            .secret(i.getClientSecret())
                            .redirectUris(i.getRedirectUri());
                    if (i.getAuthorizedGrantTypes() != null && i.getAuthorizedGrantTypes().length > 0) {
                        clientBuilder.authorizedGrantTypes(i.getAuthorizedGrantTypes());
                    }
                    if (i.getScopes() != null && i.getScopes().length > 0) {
                        clientBuilder.scopes(i.getScopes());
                    }
                });
                detailsServiceSet.add(builder.build());
            }

            boolean enabledJdbcClient =  configuredClients != null &&
                    (configuredClients.getJdbcClientsEnabled() == null || configuredClients.getJdbcClientsEnabled());
            if (enabledJdbcClient) {
                ClientDetailsService jdbcClientDetailService =  clients.jdbc(dataSource).build();
                detailsServiceSet.add(jdbcClientDetailService);
            }

            DelegateClientDetailService delegateClientDetailService = new DelegateClientDetailService(detailsServiceSet);
            clients.withClientDetails(delegateClientDetailService);

        }


        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            super.configure(endpoints);
            endpoints.accessTokenConverter(jwtAccessTokenConverter())
            .userDetailsService(userDetailsService)
            .tokenStore(new RedisTokenStore(connectionFactory));
        }


        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer)
                throws Exception {
            super.configure(oauthServer);
            oauthServer.allowFormAuthenticationForClients();
        }

    }
}
