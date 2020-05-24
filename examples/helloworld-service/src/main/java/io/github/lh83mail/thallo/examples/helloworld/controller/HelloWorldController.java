package io.github.lh83mail.thallo.examples.helloworld.controller;

import io.github.lh83mail.thallo.common.web.response.ResponseHelper;
import io.github.lh83mail.thallo.common.web.response.ResponseItem;
import io.github.lh83mail.thallo.examples.helloworld.clients.fegin.HelloWorldClient;
import io.github.lh83mail.thallo.examples.helloworld.vo.HelloWorld;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

/**
 * Hello World Controller
 */
@RestController
@RequestMapping("/hello")
@Api("微服务Hello World示例")
public class HelloWorldController {
    public final static String REMOTE_SERVICE_NAME = "examples-helloworld";

    @Autowired
    @Setter
    private HelloWorldClient helloWorldClient;


    @ApiOperation("sayHello World")
    @GetMapping
    public ResponseItem<HelloWorld> sayHello(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        String name = "无名者";
        if (jwt != null) {
            name = jwt.getClaim("user_name");
        }
        return ResponseHelper.ok(new HelloWorld(String.format( "你好, %s", name), LocalDateTime.now()));
    }


    @ApiOperation("使用Feign远程调用示例")
    @GetMapping("/feign-hello")
    public ResponseItem<HelloWorld> remoteSayHello2() {
        return helloWorldClient.sayHello();
    }
}

