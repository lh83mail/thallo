package org.halo.thallo.mmr.core.impl.service

import org.apache.ibatis.builder.BuilderException
import org.apache.ibatis.builder.SqlSourceBuilder
import org.apache.ibatis.mapping.BoundSql
import org.apache.ibatis.mapping.SqlSource
import org.apache.ibatis.reflection.ParamNameResolver
import org.apache.ibatis.session.Configuration
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
import org.springframework.context.annotation.DependsOn
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.lang.reflect.Method
import java.util.*
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
        dataStoreImpl = createDefaultDataStore(sessionFactory)
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

    @Autowired lateinit var sessionFactory: SqlSessionFactory;
    /**
     * 测试持久化新数据
     */
    @Test
    @SqlGroup(
            Sql("DataStoreImplTestCase.testPersitNewData.sql" ),
            Sql(value = ["DataStoreImplTestCase.init-after.sql"],
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    )
    fun testPersistNewData() {
        var data = mapOf(
            Pair("name","张三"),
            Pair("locked",false)
        )
        data = dataStoreImpl.persist(data) as Map<String, Any>
        assertNotNull("id should not be null", data["id"])
        assertEquals("row count should be 1", 1, countRows(sqlSession, dataStoreImpl.name, "1=1"))
    }

    @Test
    @SqlGroup(
            Sql("DataStoreImplTestCase.testDeleteData.sql" ),
            Sql(value = ["DataStoreImplTestCase.init-after.sql"],
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    )
    fun testDeleteData() {
        dataStoreImpl.delete(mapOf(Pair("id", 1)))
        assertEquals("row count should be 0", 0, countRows(sqlSession, dataStoreImpl.name, "1=1"))
    }

    @Test
    @SqlGroup(
            Sql("DataStoreImplTestCase.testDeleteData.sql" ),
            Sql(value = ["DataStoreImplTestCase.init-after.sql"],
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    )
    fun testLoadData() {
        val data = dataStoreImpl.load(mapOf(Pair("id", 1L)))
        assertNotNull("data should not be null", data)
        assertEquals("id should be 1", 1L, data["ID"])
        assertEquals(" name should be hello", "Hello", data["NAME"])
    }
}