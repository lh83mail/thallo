package io.github.lh83mail.thallo.examples.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HelloWorldMain {
    public static void main(String[] args) {
        SpringApplication.run(HelloWorldMain.class, args);
    }
}
