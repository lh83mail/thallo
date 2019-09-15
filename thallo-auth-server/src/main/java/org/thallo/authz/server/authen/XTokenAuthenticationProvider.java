package org.thallo.authz.server.authen;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;


public class XTokenAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserInfo userInfo = new UserInfo("AXOX");
//        UsernamePasswordAuthenticationToken token =  new UsernamePasswordAuthenticationToken(userInfo, "N/A");
//        token.setDetails(userInfo);
//        token.setAuthenticated(true);
        XToken token = new XToken(Arrays.asList(
                new SimpleGrantedAuthority("ADMIN")

        ), null);
        token.setDetails(userInfo);
        token.setAuthenticated(true);

        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println(authentication);
        return authentication.isAssignableFrom(XToken.class);
    }
}
