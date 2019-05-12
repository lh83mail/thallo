package org.thallo.authz.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("thallo.authzserver.security")
public class AuthzSecurityProperties {
    /**
     * 登录成功后跳转地址
     */
    private String successForwardUrl = "/";
    /**
     * 认证失败后的跳转地址
     */
    private String authenticationFailureUrl = "/login?failure";
    /**
     * 注销成功后跳转地址
     */
    private String logoutSuccessUrl =  "/login?logout";

    /**
     * 公开资源,不受权限控制地址
     */
    private String[] resources;

    public String getSuccessForwardUrl() {
        return successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
        this.successForwardUrl = successForwardUrl;
    }

    public String getAuthenticationFailureUrl() {
        return authenticationFailureUrl;
    }

    public void setAuthenticationFailureUrl(String authenticationFailureUrl) {
        this.authenticationFailureUrl = authenticationFailureUrl;
    }

    public String getLogoutSuccessUrl() {
        return logoutSuccessUrl;
    }

    public void setLogoutSuccessUrl(String logoutSuccessUrl) {
        this.logoutSuccessUrl = logoutSuccessUrl;
    }

    public String[] getResources() {
        return resources;
    }

    public void setResources(String[] resources) {
        this.resources = resources;
    }
}
