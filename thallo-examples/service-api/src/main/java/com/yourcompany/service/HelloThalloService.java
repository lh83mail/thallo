package com.yourcompany.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lihong on 17-3-15.
 */
public interface HelloThalloService {
    @RequestMapping(value = "/sayHello", method = {RequestMethod.GET})
    Thallo sayHello(@RequestParam("name") String name);
}
