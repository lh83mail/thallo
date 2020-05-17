package io.github.lh83mail.thallo.authnz.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ToString
@ConfigurationProperties("thallo.authzserver.security")
public class SecurityProperties {
    /**
     * 指定如果用户登录前未访问需要授权访问的页面，登录成功后的跳转链接
     */
    private String loginSuccessUrl = "/";

    /**
     * 登录成功后的页面视图
     */
    private String loginSuccessPage = "/authz/default/index";

    /**
     * 授权成功时的跳转链接
     */
    private String successForwardUrl;

    /**
     * 授权失败时的跳转链接
     */
    private String authenticationFailureUrl="/authz/default/login?authfail";

    /**
     * 登陆失败时跳转路径
     */
    private String loginFailureUrl = "/login?error";
    /**
     * 注销地址
     */
    private String logoutUrl = "/logout";

    /**
     * 注销成功后跳转地址
     */
    private String logoutSuccessPage =  "/login?logout";

    /**
     * 公开资源,不受权限控制地址
     */
    private String[] resources;

    /**
     * 表单登录处理地址
     */
    public String loginProcessUrl = "/form-login";

    /**
     * 表单登录视图地址
     */
    public String loginUrl = "/login";

    /**
     * 表单登录视图地址
     */
    public String loginPage = "/authz/default/login";

    /**
     * 无权访问返回地址
     */
    private String accessDeniedUrl = "/login?authorization_error";

    /**
     * 同一用户最大session数, -1（默认) 不限制
     * https://docs.spring.io/spring-security/site/docs/5.3.2.RELEASE/reference/html5/#session-mgmt
     */
    private int maximumSessions = -1;
}
