package org.thallo.starter.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "example-server", fallback = HelloThalloService.HelloThalloServiceFallback.class)
public interface HelloThalloService {
    @GetMapping(value = "/sayHello")
    Thallo sayHello(@RequestParam("name") String name);


    // FIXME 断路总是回调
    @Component
    class HelloThalloServiceFallback implements  HelloThalloService {
        @Override
        public Thallo sayHello(String name) {
            Thallo t = new Thallo();
            t.setMessage("Fallback");
            return t;
        }
    }
}
