package org.halo.thallo.mmr.core.service.impl;

import org.halo.thallo.mmr.core.service.DataStoreManager;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by lihong on 17-10-11.
 */
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:e2e/application-e2e.properties")
@MybatisTest
@EnableTransactionManagement
public class DataStoreManagerTestCase {
    private DataStoreManager dataStoreManager;
}
