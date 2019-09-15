package org.thallo.authz.server.authen;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class XToken extends AbstractAuthenticationToken {
    private String xtoken;

    public XToken(Collection<? extends GrantedAuthority> authorities, String xtoken) {
        super(authorities);
        this.xtoken = xtoken;
    }

    public String getXtoken() {
        return xtoken;
    }

    public void setXtoken(String xtoken) {
        this.xtoken = xtoken;
    }

    @Override
    public Object getCredentials() {
        return getXtoken();
    }

    @Override
    public Object getPrincipal() {
        return getXtoken();
    }
}
