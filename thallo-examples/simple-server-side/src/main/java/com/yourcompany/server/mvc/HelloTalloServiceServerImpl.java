package com.yourcompany.server.mvc;


import com.yourcompany.server.service.HelloThalloService;
import com.yourcompany.server.service.Thallo;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihong on 17-3-17.
 */
@RestController
public class HelloTalloServiceServerImpl implements HelloThalloService {
    @Override
    public Thallo sayHello(String name) {
        Thallo thallo = new Thallo();
        thallo.setMessage("Hello, " + name);
        return thallo;
    }
}
