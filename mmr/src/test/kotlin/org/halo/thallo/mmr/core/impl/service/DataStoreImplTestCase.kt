package org.halo.thallo.mmr.core.impl.service

import org.halo.thallo.mmr.core.mapper.DataStoreMapper
import org.halo.thallo.test.utils.DBTestUtils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@RunWith(SpringRunner::class)
@TestPropertySource("classpath:e2e/application-e2e.properties")
@MybatisTest
@EnableTransactionManagement
open class DataStoreImplTestCase {

    @Autowired lateinit var dataSource: DataSource
    @Autowired lateinit var dataStoreMapper: DataStoreMapper

    lateinit var dataStoreImpl: DataStoreImpl
    lateinit var dbTestUtils: DBTestUtils

    @Before
    fun setup() {
        dbTestUtils = DBTestUtils(dataSource)
        dataStoreImpl = createDefaultDataStore()
        dataStoreImpl.dataStoreMapper = dataStoreMapper
        println("here is the ii -> " + dataStoreMapper)
    }



    @Test
    @SqlGroup(
        Sql("DataStoreImplTestCase.testPerisit.sql" ),
        Sql(value = "DataStoreImplTestCase.testPerisit-after.sql",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    )
    fun testInit() {
        val tableName = dataStoreImpl.name
        
        assertFalse("initialized should be false", dataStoreImpl.initialized)
        dataStoreImpl.init()
        // assert table has been created
        assertTrue("table should be exists in database", dbTestUtils.isTableExists(tableName))
        assertTrue("initialized should be true", dataStoreImpl.initialized)
        // assert store state should be true
    }


}
