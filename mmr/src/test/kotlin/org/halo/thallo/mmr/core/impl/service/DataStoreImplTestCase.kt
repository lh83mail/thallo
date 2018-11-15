package org.halo.thallo.mmr.core.impl.service

import org.apache.commons.dbutils.QueryRunner
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import org.halo.thallo.mmr.core.mapper.DataStoreMapper
import org.halo.thallo.test.utils.DBTestUtils
import org.halo.thallo.test.utils.countRows
import org.junit.Assert.*
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
import java.sql.Connection
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
    @Autowired lateinit var sqlSession:SqlSession

    @Before
    fun setup() {
        dbTestUtils = DBTestUtils(dataSource)
        dataStoreImpl = createDefaultDataStore()
        dataStoreImpl.dataStoreMapper = dataStoreMapper
    }

    /**
     * 创建数据结构
     */
    @Test
    @SqlGroup(
        Sql("DataStoreImplTestCase.init.sql" ),
        Sql(value = ["DataStoreImplTestCase.init-after.sql"],
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    )
    fun testInit() {
        val tableName = dataStoreImpl.name
        
        assertFalse("initialized should be false", dataStoreImpl.initialized)
        dataStoreImpl.init()
        // assert table has been created
        assertTrue("table should be exists in database", dbTestUtils.isTableExists(tableName))
        assertTrue("initialized should be true", dataStoreImpl.initialized)
    }

    /**
     * 测试持久化新数据
     */
    @Test
    @SqlGroup(
            Sql("DataStoreImplTestCase.testPersitNewData.sql" )
    )
    fun testPersistNewData() {
        val data = mapOf(
            Pair("id",1),
            Pair("name","张三"),
            Pair("locked",false)
        )

        dataStoreImpl.persist(data)

        assertEquals("row should be exists", 1, countRows(sqlSession, dataStoreImpl.name, "id=1"))
    }

}
