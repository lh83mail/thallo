package org.thallo.authz.server.endpoints;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/**
 * 用户根据自定义方式生成特定令牌
 */
@FrameworkEndpoint
public class CusTokenEndpoints {

    private AuthorizationServerTokenServices tokenService;

    @Autowired
    public CusTokenEndpoints(AuthorizationServerTokenServices tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/custoken")
    @ResponseBody
    public OAuth2AccessToken getKey() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("admin", "N/A", Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ANYONE")
        ));
        OAuth2Request request = new OAuth2Request(null, "acme", null, true, null, null, null, null, null);
        OAuth2Authentication auth2Authentication = new OAuth2Authentication(request, authentication);
        return this.tokenService.createAccessToken(auth2Authentication);
    }
}
