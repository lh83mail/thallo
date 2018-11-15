package org.halo.thallo.mmr.core.impl.service

import org.apache.ibatis.builder.BuilderException
import org.apache.ibatis.builder.SqlSourceBuilder
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator
import org.apache.ibatis.mapping.BoundSql
import org.apache.ibatis.mapping.MappedStatement
import org.apache.ibatis.mapping.SqlCommandType
import org.apache.ibatis.mapping.SqlSource
import org.apache.ibatis.reflection.ParamNameResolver
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import org.halo.thallo.mmr.core.mapper.DataStoreMapper
import org.halo.thallo.mmr.core.mapper.InsertProvider
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
import java.lang.reflect.Method
import java.util.HashMap
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

//    @Autowired lateinit var configuration: Configuration;
    @Autowired lateinit var sessionFactory: SqlSessionFactory;
    /**
     * 测试持久化新数据
     */
    @Test
    @SqlGroup(
            Sql("DataStoreImplTestCase.testPersitNewData.sql" )
    )
    fun testPersistNewData() {
        val data = mapOf(
//            Pair("id",1),
            Pair("name","张三"),
            Pair("locked",false)
        )

        val configuration = sessionFactory.configuration
        val pro:SqlSource = MyProviderSqlSource(configuration, InsertProvider())
        val mappedStatement = MappedStatement.Builder(configuration,"test_id", pro, SqlCommandType.INSERT)
        mappedStatement.keyProperty("id")
        mappedStatement.keyGenerator(Jdbc3KeyGenerator.INSTANCE)
        configuration.addMappedStatement(mappedStatement.build())

    threadLocal.set(dataStoreImpl)
       sqlSession.insert("test_id", data)
      println(data)
        sqlSession.insert("test_id", data)
        println(data)
        sqlSession.insert("test_id", data)
        println(data)
        sqlSession.insert("test_id", data)
        println(data)
//      println(  dataStoreImpl.persist(data))
//        dataStoreImpl.persist(data)

        assertEquals("row count should be 1", 1, countRows(sqlSession, dataStoreImpl.name, "1=1"))
    }


}

class MyProviderSqlSource ( val config:Configuration, val provider:Any): SqlSource {

    private var sqlSourceParser: SqlSourceBuilder? = null
    private var providerType: Class<*>? = null
    private var providerMethod: Method? = null
    private var providerMethodArgumentNames: Array<String>? = null

    init {
        this.sqlSourceParser = SqlSourceBuilder(config)
        this.providerType = provider::class.java
        this.providerMethod = provider::class.java.methods.get(0)
        this.providerMethodArgumentNames = ParamNameResolver(config, this.providerMethod).names

    }
//    fun ProviderSqlSource(config: Configuration, provider: Any): ??? {
//        val providerMethodName: String
//        try {
//            this.sqlSourceParser = SqlSourceBuilder(config)
//            this.providerType = provider.javaClass.getMethod("type").invoke(provider) as Class<*>
//            providerMethodName = provider.javaClass.getMethod("method").invoke(provider) as String
//
//            for (m in this.providerType!!.methods) {
//                if (providerMethodName == m.name) {
//                    if (m.returnType == String::class.java) {
//                        if (providerMethod != null) {
//                            throw BuilderException("Error creating SqlSource for SqlProvider. Method '"
//                                    + providerMethodName + "' is found multiple in SqlProvider '" + this.providerType!!.name
//                                    + "'. Sql provider method can not overload.")
//                        }
//                        this.providerMethod = m
//                        this.providerMethodArgumentNames = ParamNameResolver(config, m).names
//                    }
//                }
//            }
//        } catch (e: BuilderException) {
//            throw e
//        } catch (e: Exception) {
//            throw BuilderException("Error creating SqlSource for SqlProvider.  Cause: $e", e)
//        }
//
//        if (this.providerMethod == null) {
//            throw BuilderException("Error creating SqlSource for SqlProvider. Method '"
//                    + providerMethodName + "' not found in SqlProvider '" + this.providerType!!.name + "'.")
//        }
//    }

    override fun getBoundSql(parameterObject: Any): BoundSql {
        val sqlSource = createSqlSource(parameterObject)
        return sqlSource.getBoundSql(parameterObject)
    }

    private fun createSqlSource(parameterObject: Any?): SqlSource {
        try {
            val parameterTypes = providerMethod!!.parameterTypes
            val sql: String
            if (parameterTypes.size == 0) {
                sql = providerMethod!!.invoke(providerType!!.newInstance()) as String
            } else if (parameterTypes.size == 1 && (parameterObject == null || parameterTypes[0].isAssignableFrom(parameterObject.javaClass))) {
                sql = providerMethod!!.invoke(providerType!!.newInstance(), parameterObject) as String
            } else if (parameterObject is Map<*, *>) {
                val params = parameterObject as Map<String, Any>?
                sql = providerMethod!!.invoke(providerType!!.newInstance(), *extractProviderMethodArguments(params!!, providerMethodArgumentNames)) as String
            } else {
                throw BuilderException("Error invoking SqlProvider method ("
                        + providerType!!.name + "." + providerMethod!!.name
                        + "). Cannot invoke a method that holds "
                        + (if (parameterTypes.size == 1) "named argument(@Param)" else "multiple arguments")
                        + " using a specifying parameterObject. In this case, please specify a 'java.util.Map' object.")
            }
            val parameterType = parameterObject?.javaClass ?: Any::class.java
            return sqlSourceParser!!.parse(sql, parameterType, HashMap())
        } catch (e: BuilderException) {
            throw e
        } catch (e: Exception) {
            throw BuilderException("Error invoking SqlProvider method ("
                    + providerType!!.name + "." + providerMethod!!.name
                    + ").  Cause: " + e, e)
        }

    }

    private fun extractProviderMethodArguments(params: Map<String, Any>, argumentNames: Array<String>?): Array<Any?> {
        val args = arrayOfNulls<Any>(argumentNames!!.size)
        for (i in args.indices) {
            args[i] = params[argumentNames[i]]
        }
        return args
    }

}