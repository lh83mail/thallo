package io.github.lh83mail.thallo.authnz.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import io.github.lh83mail.thallo.authnz.oauth2.DelegateClientDetailService;

import javax.sql.DataSource;
import java.util.Optional;

public class Oauth2ServerConfiguration {
    private static final String SPARKLR_RESOURCE_ID = "sparklr";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(SPARKLR_RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    // Since we want the protected resources to be accessible in the UI as well we need
                    // session creation to be allowed (it's disabled by default in 2.0.6)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().antMatchers("/photos/**", "/oauth/users/**", "/oauth/clients/**","/me")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/me").access("#oauth2.hasScope('read')")
                    .antMatchers("/photos").access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")
                    .antMatchers("/photos/trusted/**").access("#oauth2.hasScope('trust')")
                    .antMatchers("/photos/user/**").access("#oauth2.hasScope('trust')")
                    .antMatchers("/photos/**").access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")
                    .regexMatchers(HttpMethod.DELETE, "/oauth/users/([^/].*?)/tokens/.*")
                    .access("#oauth2.clientHasRole('ROLE_CLIENT') and (hasRole('ROLE_USER') or #oauth2.isClient()) and #oauth2.hasScope('write')")
                    .regexMatchers(HttpMethod.GET, "/oauth/clients/([^/].*?)/users/.*")
                    .access("#oauth2.clientHasRole('ROLE_CLIENT') and (hasRole('ROLE_USER') or #oauth2.isClient()) and #oauth2.hasScope('read')")
                    .regexMatchers(HttpMethod.GET, "/oauth/clients/.*")
                    .access("#oauth2.clientHasRole('ROLE_CLIENT') and #oauth2.isClient() and #oauth2.hasScope('read')");
            // @formatter:on
        }

    }

    @Configuration
    @EnableAuthorizationServer
    @EnableConfigurationProperties({ OAuth2ServerProperties.class })
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private OAuth2ServerProperties oauth2ServerProperties;
        @Autowired
        private DataSource dataSource;
        @Autowired
        private TokenStore tokenStore;


//        @Autowired
//        private UserApprovalHandler userApprovalHandler;
//
//        @Autowired
//        @Qualifier("authenticationManagerBean")
//        private AuthenticationManager authenticationManager;
//
//        @Value("${tonr.redirect:http://localhost:8080/tonr2/sparklr/redirect}")
//        private String tonrRedirectUri;


        @Bean
        public TokenStore redisTokenStore(RedisConnectionFactory connectionFactory) {
            return new RedisTokenStore(connectionFactory);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            DelegateClientDetailService delegateClientDetailService = new DelegateClientDetailService();
            this.configure(delegateClientDetailService, clients);
            clients.withClientDetails(delegateClientDetailService);
        }

        /**
         * 配置客户端服务
         * @param delegateClientDetailService
         * @param clients
         */
        protected void configure(DelegateClientDetailService delegateClientDetailService, ClientDetailsServiceConfigurer clients) throws Exception {
            OAuth2ServerProperties.Clients configuredClients = oauth2ServerProperties.getClients();

            // build In Memory Clients
            boolean memoryClientsEnabled = Optional.ofNullable(configuredClients)
                    .map(OAuth2ServerProperties.Clients::getMemoryClientsEnabled)
                    .orElse(false);
            if (memoryClientsEnabled) {
                Optional.ofNullable(configuredClients.getClientDetails())
                        .filter(details -> !details.isEmpty())
                        .ifPresent(details -> {
                            try {
                                ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder> clientBuilder = clients.inMemory();
                                for(OAuth2ServerProperties.MemoryClientDetails i: details) {
                                    ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder c =  clientBuilder
                                            .withClient(i.getClientId())
                                            .secret(i.getClientSecret())
                                            .redirectUris(i.getRedirectUri());
                                    if (i.getAuthorizedGrantTypes() != null && i.getAuthorizedGrantTypes().length > 0) {
                                        c.authorizedGrantTypes(i.getAuthorizedGrantTypes());
                                    }
                                    if (i.getScopes() != null && i.getScopes().length > 0) {
                                        c.scopes(i.getScopes());
                                    }
                                    c.autoApprove(false);
                                    clientBuilder = c.and();
                                }
                                delegateClientDetailService.addClientDetailServices(clientBuilder.build());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
            }


            boolean jdbcClientsEnabled = Optional.ofNullable(configuredClients)
                    .map(OAuth2ServerProperties.Clients::getJdbcClientsEnabled)
                    .orElse(false);
            if (jdbcClientsEnabled) {
                ClientDetailsService jdbcClientDetailService =  clients.jdbc(dataSource).build();
                delegateClientDetailService.addClientDetailServices(jdbcClientDetailService);
            }

        }


//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//            endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
//                    .authenticationManager(authenticationManager);
//        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.realm("sparklr2/client");
        }
    }
//
//    protected static class Stuff {
//
//        @Autowired
//        private ClientDetailsService clientDetailsService;
//
//        @Autowired
//        private TokenStore tokenStore;
//
//        @Bean
//        public ApprovalStore approvalStore() throws Exception {
//            TokenApprovalStore store = new TokenApprovalStore();
//            store.setTokenStore(tokenStore);
//            return store;
//        }
//
//        @Bean
//        @Lazy
//        @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
//        public SparklrUserApprovalHandler userApprovalHandler() throws Exception {
//            SparklrUserApprovalHandler handler = new SparklrUserApprovalHandler();
//            handler.setApprovalStore(approvalStore());
//            handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
//            handler.setClientDetailsService(clientDetailsService);
//            handler.setUseApprovalStore(true);
//            return handler;
//        }
//    }
}
