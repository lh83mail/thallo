package org.thallo.authz.server.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户根据自定义方式生成特定令牌
 */
@FrameworkEndpoint
@RequestMapping("/auth")
public class ApiAuthonEndpoints {

    private AuthorizationServerTokenServices tokenService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public ApiAuthonEndpoints(AuthorizationServerTokenServices tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, Object> login(@RequestBody ModelMap values) {
        String username = (String) values.get("username");
        String password = (String) values.get("password");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        OAuth2Request request = new OAuth2Request(null, "acme", null, true, null, null, null, null, null);
        OAuth2Authentication auth2Authentication = new OAuth2Authentication(request, authentication);

        OAuth2AccessToken accessToken = this.tokenService.createAccessToken(auth2Authentication);
        Map<String, Object> result = new HashMap<>();
        result.put("user", auth2Authentication);
        result.put("token", accessToken);
        return result;
    }
}
