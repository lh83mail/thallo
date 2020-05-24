package io.github.lh83mail.thallo.examples.helloworld.clients.fegin;

import feign.hystrix.FallbackFactory;
import io.github.lh83mail.thallo.common.web.response.ResponseItem;
import io.github.lh83mail.thallo.examples.helloworld.vo.HelloWorld;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

import static io.github.lh83mail.thallo.common.web.response.ResponseHelper.ok;
import static io.github.lh83mail.thallo.examples.helloworld.controller.HelloWorldController.REMOTE_SERVICE_NAME;

@FeignClient( name = REMOTE_SERVICE_NAME, contextId = "HelloWorldClient", fallbackFactory = HelloWorldClient.HelloWorldClientFallbackFactory.class)
public interface HelloWorldClient {
    @GetMapping("/hello")
    ResponseItem<HelloWorld> sayHello();


    @Component
    class HelloWorldClientFallbackFactory implements FallbackFactory<HelloWorldClient> {
        @Override
        public HelloWorldClient create(Throwable cause) {
           return new HelloWorldClient() {
               @Override
               public ResponseItem<HelloWorld> sayHello() {
                   return ok(new HelloWorld("远端服务发生了一些异常, 服务降级: " + cause.getMessage(),  LocalDateTime.now()));
               }
           };
        }
    }
}
