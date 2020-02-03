package com.yourcompany.simple.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create At  2020/1/6 14:11
 *
 * @author: Lh
 * @version: 1.0.0
 */
@RestController
@RefreshScope
public class SayHelloController {
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @GetMapping("/hello")
    public String get(String name) {
        return String.format("Hello, %s. useLocalCache: %b", name, useLocalCache);
    }
}
