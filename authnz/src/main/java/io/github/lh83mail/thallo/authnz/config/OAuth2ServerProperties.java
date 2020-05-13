package io.github.lh83mail.thallo.authnz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * 授权服务配置
 */
@Data
@ConfigurationProperties("thallo.authzserver.oauth2")
public class OAuth2ServerProperties {
    /**
     * 配置授权客户端信息
     */
    private Clients clients;

    @Data
    public static class Clients {
        /**
         * 开启内存环境
         */
        private Boolean memoryClientsEnabled;
        /**
         * 开启jdbc环境
         */
        private Boolean jdbcClientsEnabled;

        private Set<MemoryClientDetails> clientDetails;
    }

    @Data
    public static class MemoryClientDetails {
        private String clientId;
        private String clientSecret;
        private String[] redirectUri;
        private String[] authorizedGrantTypes;
        private String[] scopes;
    }
}

