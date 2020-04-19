package com.yourcompany.server.mvc;


import com.yourcompany.server.service.HelloThalloService;
import com.yourcompany.server.service.Thallo;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import java.security.Principal;

/**
 * Created by lihong on 17-3-17.
 */
@RestController
public class HelloTalloServiceServerImpl implements HelloThalloService {
    @Override
    public Thallo sayHello(String name) {
//        Principal principal = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getUserPrincipal();
        Thallo thallo = new Thallo();
        thallo.setMessage("Hello, " + name);
//        thallo.setPrincipal(principal.getName());
        return thallo;
    }
}
