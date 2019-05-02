package org.thallo.authz.server.oauth2;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

@ConfigurationProperties("thallo.authzserver.security.oauth2")
public class ThalloAuthzServerOauth2Properties {
    /**
     * 配置授权客户端信息
     */
    private Clients clients;


    public Clients getClients() {
        return clients;
    }

    public void setClients(Clients clients) {
        this.clients = clients;
    }

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

        public Set<MemoryClientDetails> getClientDetails() {
            return clientDetails;
        }

        public void setClientDetails(Set<MemoryClientDetails> clientDetails) {
            this.clientDetails = clientDetails;
        }

        public Boolean getMemoryClientsEnabled() {
            return memoryClientsEnabled;
        }

        public void setMemoryClientsEnabled(Boolean memoryClientsEnabled) {
            this.memoryClientsEnabled = memoryClientsEnabled;
        }

        public Boolean getJdbcClientsEnabled() {
            return jdbcClientsEnabled;
        }

        public void setJdbcClientsEnabled(Boolean jdbcClientsEnabled) {
            this.jdbcClientsEnabled = jdbcClientsEnabled;
        }
    }

    public static class MemoryClientDetails {
        private String clientId;
        private String clientSecret;
        private String[] redirectUri;
        private String[] authorizedGrantTypes;
        private String[] scopes;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String[] getRedirectUri() {
            return redirectUri;
        }

        public void setRedirectUri(String[] redirectUri) {
            this.redirectUri = redirectUri;
        }

        public String[] getAuthorizedGrantTypes() {
            return authorizedGrantTypes;
        }

        public void setAuthorizedGrantTypes(String[] authorizedGrantTypes) {
            this.authorizedGrantTypes = authorizedGrantTypes;
        }

        public String[] getScopes() {
            return scopes;
        }

        public void setScopes(String[] scopes) {
            this.scopes = scopes;
        }


    }
}

