package io.github.lh83mail.thallo.authnz.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.github.lh83mail.thallo.authnz.oauth2.DelegateClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.ApprovalStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.io.InputStream;
import java.security.*;
import java.util.Map;
import java.util.Optional;

@Configuration
public class Oauth2ServerConfiguration {

    public static final String WELL_KNOWN_JWKS_JSON_URI = "/.well-known/jwks.json";
    /**
     * OAuth2AccessToken 令牌存储库
     */
    @Bean
    public TokenStore redisTokenStore(RedisConnectionFactory connectionFactory) {
        return new RedisTokenStore(connectionFactory);
    }

//    @Configuration
//    @EnableResourceServer
//    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            // @formatter:off
//            http
//                    // Since we want the protected resources to be accessible in the UI as well we need
//                    // session creation to be allowed (it's disabled by default in 2.0.6)
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                    .and()
//                    .requestMatchers().antMatchers("/photos/**", "/oauth/users/**", "/oauth/clients/**","/me")
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/me").access("#oauth2.hasScope('read')")
//                    .antMatchers("/photos").access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")
//                    .antMatchers("/photos/trusted/**").access("#oauth2.hasScope('trust')")
//                    .antMatchers("/photos/user/**").access("#oauth2.hasScope('trust')")
//                    .antMatchers("/photos/**").access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))")
//                    .regexMatchers(HttpMethod.DELETE, "/oauth/users/([^/].*?)/tokens/.*")
//                    .access("#oauth2.clientHasRole('ROLE_CLIENT') and (hasRole('ROLE_USER') or #oauth2.isClient()) and #oauth2.hasScope('write')")
//                    .regexMatchers(HttpMethod.GET, "/oauth/clients/([^/].*?)/users/.*")
//                    .access("#oauth2.clientHasRole('ROLE_CLIENT') and (hasRole('ROLE_USER') or #oauth2.isClient()) and #oauth2.hasScope('read')")
//                    .regexMatchers(HttpMethod.GET, "/oauth/clients/.*")
//                    .access("#oauth2.clientHasRole('ROLE_CLIENT') and #oauth2.isClient() and #oauth2.hasScope('read')");
//            // @formatter:on
//        }
//
//    }


    @Configuration
    @EnableAuthorizationServer
    @EnableConfigurationProperties({ OAuth2ServerProperties.class, AuthorizationServerProperties.class })
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private OAuth2ServerProperties oauth2ServerProperties;
        @Autowired
        private DataSource dataSource;
        @Autowired
        private AuthorizationServerProperties properties;
        @Autowired
        private TokenStore tokenStore;
        @Autowired
        private ApplicationContext context;

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
                ClientDetailsService jdbcClientDetailService = clients.jdbc(dataSource).build();
                delegateClientDetailService.addClientDetailServices(jdbcClientDetailService);
            }

        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security)
                throws Exception {
            security.passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
            if (this.properties.getCheckTokenAccess() != null) {
                security.checkTokenAccess(this.properties.getCheckTokenAccess());
            }
            if (this.properties.getTokenKeyAccess() != null) {
                security.tokenKeyAccess(this.properties.getTokenKeyAccess());
            }
            if (this.properties.getRealm() != null) {
                security.realm(this.properties.getRealm());
            }
        }


        @Bean
        public KeyStore keystore() throws Exception {
            Assert.notNull(this.properties.getJwt().getKeyStore(), "keyStore cannot be null");
            Assert.notNull(this.properties.getJwt().getKeyStorePassword(), "keyStorePassword cannot be null");

            Resource keyStore = this.context.getResource(this.properties.getJwt().getKeyStore());
            char[] keyStorePassword = this.properties.getJwt().getKeyStorePassword().toCharArray();
            try (InputStream in = keyStore.getInputStream()) {
                KeyStore store = KeyStore.getInstance("PKCS12");
                store.load(in, keyStorePassword);
                return store;
            }
        }

        @Bean
        public JwtAccessTokenConverter accessTokenConverter(KeyStore keyStore) throws Exception {
            Assert.notNull(this.properties.getJwt().getKeyAlias(), "keyAlias cannot be null");
            Assert.notNull(this.properties.getJwt().getKeyStorePassword(), "keyStorePassword cannot be null");

            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            //
            char[] keyStorePassword = this.properties.getJwt().getKeyStorePassword().toCharArray();
            String keyAlias = this.properties.getJwt().getKeyAlias();
            char[] keyPassword = Optional.ofNullable(
                    this.properties.getJwt().getKeyPassword())
                    .map(String::toCharArray).orElse(keyStorePassword);

            Key key = keyStore.getKey(keyAlias, keyPassword);
            converter.setKeyPair(new KeyPair(keyStore.getCertificate(keyAlias).getPublicKey(), (PrivateKey) key));

            return converter;
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            if (this.tokenStore != null) {
                endpoints.tokenStore(this.tokenStore);
            }
            endpoints
                 .accessTokenConverter(accessTokenConverter(keystore()));
        }

        @FrameworkEndpoint
        class JwkSetEndpoint {
            private final KeyStore keyStore;
            private final AuthorizationServerProperties properties;

            JwkSetEndpoint(KeyStore keyStore, AuthorizationServerProperties properties) {
                this.keyStore = keyStore;
                this.properties = properties;
            }

            @GetMapping(WELL_KNOWN_JWKS_JSON_URI)
            @ResponseBody
            public Map<String, Object> getKey() throws KeyStoreException, JOSEException {
                char[] keyStorePassword = this.properties.getJwt().getKeyStorePassword().toCharArray();
                RSAKey rsaKey = RSAKey.load(this.keyStore, properties.getJwt().getKeyAlias(), keyStorePassword);
                return new JWKSet(rsaKey).toJSONObject();
            }
        }
    }

    @Configuration
    protected static class OauthServerConfiguration implements WebMvcConfigurer {
        @Autowired
        private OAuth2ServerProperties oauth2ServerProperties;

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/oauth/confirm_access")
                    .setViewName(oauth2ServerProperties.getConfirm_accessPage());
        }
    }

    protected static class UserApprovalHandlerConfiguration {

        @Autowired
        private ClientDetailsService clientDetailsService;

        @Autowired
        private TokenStore tokenStore;

        @Bean
        public ApprovalStore approvalStore() throws Exception {
            TokenApprovalStore store = new TokenApprovalStore();
            store.setTokenStore(tokenStore);
            return store;
        }

        @Bean
        @Lazy
        @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
        public ApprovalStoreUserApprovalHandler userApprovalHandler() throws Exception {
            return new ApprovalStoreUserApprovalHandler();
        }
    }

}
