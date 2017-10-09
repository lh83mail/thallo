package org.halo.thallo.mmr.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by lihong on 17-10-9.
 */
@TestConfiguration
@SpringBootApplication
@EnableTransactionManagement
@EntityScan
public class ServiceTestRoot {
}
