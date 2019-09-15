package org.thallo.authz.server.authen;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class XTokenFilter extends AbstractAuthenticationProcessingFilter {

    public XTokenFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        setAllowSessionCreation(false);
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String x = request.getHeader("Authorization");
        if (x != null && x.startsWith("XTOKEN ")) {
            String t = x.substring("XTOKEN ".length());
            return getAuthenticationManager().authenticate(new XToken(null, t));
        }
        return  null;
    }
}
