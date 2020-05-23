package io.github.lh83mail.thallo.examples.helloworld.controller;

import io.github.lh83mail.thallo.common.web.response.ResponseHelper;
import io.github.lh83mail.thallo.common.web.response.ResponseItem;
import io.github.lh83mail.thallo.examples.helloworld.vo.HelloWorld;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("sayHello World")
    @GetMapping
    public ResponseItem<HelloWorld> sayHello(@AuthenticationPrincipal Jwt jwt, Principal principal) {
        String name = "无名者";
        if (jwt != null) {
            name = jwt.getClaim("user_name");
        }
        return ResponseHelper.ok(new HelloWorld(String.format( "你好, %s", name), LocalDateTime.now()));
    }
}

