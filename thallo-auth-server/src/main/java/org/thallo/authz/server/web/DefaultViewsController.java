package org.thallo.authz.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thallo.authz.server.authen.UserInfo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DefaultViewsController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthorizationServerTokenServices tokenService;

    @GetMapping("/info")
    @ResponseBody
    public Object info() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/api/login")
    @ResponseBody
    public Object info(@RequestBody ModelMap values) {

        String username = (String) values.get("username");
        String password = (String) values.get("password");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        OAuth2Request request = new OAuth2Request(null, "acme", null, true, null, null, null, null, null);
        OAuth2Authentication auth2Authentication = new OAuth2Authentication(request, authentication);

        Map<String, Object> result = new HashMap<>();
        result.put("user", auth2Authentication);
        result.put("token", this.tokenService.createAccessToken(auth2Authentication));
        return result;
    }


}
