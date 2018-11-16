package org.halo.thallo.mmr.core.service.impl;

import org.halo.thallo.mmr.core.impl.service.DataStoreImpl;
import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
import org.halo.thallo.mmr.core.model.DataSchema;
import org.halo.thallo.mmr.core.service.MMRException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

import static org.halo.thallo.mmr.core.service.impl.Util.createUnitTestDataObject;
import static org.junit.Assert.assertTrue;

/**
 * Created by lihong on 17-10-17.
 */
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:e2e/application-e2e.properties")
@MybatisTest
@EnableTransactionManagement
public class DataStoreImplTestCase {
    @Autowired
    private DataStoreMapper dataStoreMapper;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private DataStoreImpl dataStore;

    @Before
    public void setup() {
        DataSchema unitTestDataObject = createUnitTestDataObject();
//        dataStore = new DataStoreImpl.kt(unitTestDataObject, dataStoreMapper);
//        dataStore.setJdbcTemplate(jdbcTemplate);
        throw new RuntimeException("do sth.");
    }
    /**
     * 创建新表
     */
    @Test
    public void testDataStoreCreate()  {
//        boolean resulst = dataStore.create();
        assertTrue(false);
    }

    @Sql("DataStoreImplTestCase.init.sql")
    @Sql(value = "DataStoreImplTestCase.init-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testPerisit() throws MMRException {
        Map<String, Object> values = new HashMap<>();
      //  values.put("id", 1);
        values.put("name", "TestData1");
        values.put("locked", false);
throw new RuntimeException("should test sth");
//        DataSchema dataObject = dataStore.persist(values);
//        System.out.println(dataObject);
    }

}
