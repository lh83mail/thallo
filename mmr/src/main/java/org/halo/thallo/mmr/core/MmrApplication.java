package org.halo.thallo.mmr.core;

import org.halo.thallo.mmr.core.config.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by dell01 on 2017/9/30.
 */
@SpringBootApplication
@Import({
    JpaConfiguration.class
})
public class MmrApplication {
    public static void main(String[] args) {
        SpringApplication.run(MmrApplication.class, args);
    }
}
