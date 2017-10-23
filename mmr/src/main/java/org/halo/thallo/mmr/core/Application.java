package org.halo.thallo.mmr.core;

import org.halo.thallo.mmr.core.config.MybatisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by lihong on 17-10-10.
 */
@SpringBootApplication
@EnableTransactionManagement
@Import({MybatisConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
