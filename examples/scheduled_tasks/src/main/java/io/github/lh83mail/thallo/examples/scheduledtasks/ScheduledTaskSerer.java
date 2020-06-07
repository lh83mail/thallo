package io.github.lh83mail.thallo.examples.scheduledtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ScheduledTaskSerer {
    public static void main(String[] args) {
        SpringApplication.run(ScheduledTaskSerer.class, args);
    }
}
