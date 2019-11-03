package com.yourcompany.server.mvc;


import com.yourcompany.server.service.HelloThalloService;
import com.yourcompany.server.service.Thallo;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by lihong on 17-3-17.
 */
@RestController
public class HelloTalloServiceServerImpl implements HelloThalloService {
    @Override
    public Thallo sayHello(String name, Principal principal) {
        Thallo thallo = new Thallo();
        thallo.setMessage("Hello, " + name);
        thallo.setPrincipal(principal);
        return thallo;
    }
}
