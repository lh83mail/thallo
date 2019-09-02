package org.thallo.authz.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("thallo.authzserver.security")
public class AuthzSecurityProperties {
    /**
     * 指定如果用户登录前未访问需要授权访问的页面，登录成功后的跳转链接
     */
    private String defaultSuccessUrl = "/";

    /**
     * 授权成功时的跳转链接
     */
    private String successForwardUrl;

    /**
     * 授权失败时的跳转链接
     */
    private String authenticationFailureUrl="/login?authfail";

    /**
     * 登陆失败时跳转路径
     */
    private String failureUrl = "/login?error";

    /**
     * 注销成功后跳转地址
     */
    private String logoutSuccessUrl =  "/login?logout";

    /**
     * 公开资源,不受权限控制地址
     */
    private String[] resources;

    public String getDefaultSuccessUrl() {
        return defaultSuccessUrl;
    }

    public void setDefaultSuccessUrl(String defaultSuccessUrl) {
        this.defaultSuccessUrl = defaultSuccessUrl;
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

    public String getSuccessForwardUrl() {
        return successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
        this.successForwardUrl = successForwardUrl;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }
}
