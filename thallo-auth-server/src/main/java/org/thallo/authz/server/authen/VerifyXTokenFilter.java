package org.thallo.authz.server.authen;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


public class VerifyXTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            String x = request.getHeader("Authorization");
            if (x != null && x.startsWith("XTOKEN ")) {
                String t = x.substring("XTOKEN ".length());

                UserInfo userInfo = new UserInfo("AXOX");
                XToken token = new XToken(Arrays.asList(
                        new SimpleGrantedAuthority("ADMIN")

                ), t);
                token.setDetails(userInfo);

                token.setAuthenticated(true);
                SecurityContextHolder.getContext().setAuthentication(token);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request,response);
    }
}
