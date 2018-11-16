package org.halo.thallo.mmr.core.impl.service

import com.alibaba.fastjson.JSONObject
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator
import org.apache.ibatis.mapping.ResultMap
import org.apache.ibatis.mapping.SqlCommandType
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactory
import org.halo.thallo.mmr.core.impl.config.AbstractModel
import org.halo.thallo.mmr.core.mapper.DataStoreMapper
import org.halo.thallo.mmr.core.model.AUTO_INCREMENT
import org.halo.thallo.mmr.core.model.Attribute
import org.halo.thallo.mmr.core.model.DataSchema
import org.halo.thallo.mmr.core.model.DataStore
import org.halo.thallo.mmr.core.mybatis.prepareMybatisStatements

class DataStoreImpl (val sessionFactory:SqlSessionFactory,  config: JSONObject?) : AbstractModel(config), DataSchema, DataStore {

    /**
     * 已经初始化
     */
    var initialized = false
        private set

    lateinit var dataStoreMapper: DataStoreMapper

    /**
     * 列定义映射
     */
    var dbDefintionMapper = DbDefintionMapper()
    /**
     * 所有属性列表
     */
    var attributes: MutableList<Attribute>? = null

    init {
        if (config != null) {
            if (config.containsKey("attributes")) {
                val array = config.getJSONArray("attributes")
                array.forEach { arr -> this.addAttributes(AttributeImpl(arr as JSONObject)) }
            }
        }
    }

    /**
     * 获取属性列表
     */
    override fun getAttributes(): Iterable<Attribute> {
        return if (this.attributes == null) {
            listOf<Attribute>()
        } else {
            this.attributes!!.toList()
        }
    }

    /**
     * 添加属性列表
     */
    override fun addAttributes(vararg attributes: Attribute) {

        if (this.attributes == null) {
            this.attributes = ArrayList()
        }

        for (attribute in attributes) {
            this.attributes!!.add(attribute)
        }
    }

    /**
     * 复制对象
     */
    @Throws(CloneNotSupportedException::class)
    override fun clone(): DataStoreImpl {
        val impl = super.clone() as DataStoreImpl
        if (attributes != null) {
            val templst = ArrayList<Attribute>()
            for (a in attributes!!) {
                templst.add(a.clone() as Attribute)
            }
            impl.attributes = templst
        }
        return impl
    }

    /**
     * 获取标识属性
     */
    override fun getIdAttributes(): List<Attribute> {
        return if (attributes == null) {
            emptyList()
        } else {
            attributes!!.filter { it.isPrimary }
        }
    }

    /**
     * 创建数据表结构
     */
    override fun init() {
        this.dataStoreMapper.execute(generateCreateTableSql().toString())
        this.initialized = true
        // 创建插入代码块
        prepareInsertStatement()
        this.dataStoreMapper.updateDataStore(this)
    }


    /**
     * 生成创建数据表的sql
     */
    private fun generateCreateTableSql(): StringBuffer {
        val buf = StringBuffer("create table ${this.name} (")

        this.attributes?.forEach {
            buf.append(dbDefintionMapper.columnDefintion(it))
            if (it.valueProvider == AUTO_INCREMENT) {
                buf.append(" auto_increment ")
            }
            buf.append(",")
        }
        val names = this.idAttributes.map { it.name }
        buf.append(" primary key (${names.joinToString(",")})")
        buf.append(")")
        return buf
    }

    /**
     * 持久化数据到DataStore中
     */
    override fun persist(data: Map<String, *>): Map<String, *> {
        ensureInsertStatementPrepared()
        execute { sqlSession ->
            sqlSession.insert(getInsertStatementId(), data)
        }
        return data
    }

    private fun execute(process: (SqlSession) -> Any?): Any? {
        val sqlSession = sessionFactory.openSession()
        return sqlSession.use { sqlSession ->
            process(sqlSession)
        }
    }

    override fun delete(keys: Map<String, Any>) {
        ensureDeleteStatementPrepared()
        execute {
            it.delete(getDeleteStatementId(), keys)
        }
    }

    override fun load(keys: Map<String, Any>): MutableMap<String, *> {
        ensureLoadStatementPrepared()
       return execute {
          it.selectOne(getLoadStatementId(), keys)
        } as MutableMap<String, *>
    }


    fun getInsertStatementId() = "${id}!DYN_INSERT"

    /**
     * 准备初始化插入方法
     */
    fun prepareInsertStatement() {
        prepareMybatisStatements(sessionFactory!!.configuration, getInsertStatementId(), SqlProvider(this), "insert", SqlCommandType.INSERT) {
            idAttributes.forEach { key ->
                if (key.valueProvider == AUTO_INCREMENT) {
                    it.keyProperty(key.name)
                    it.keyGenerator(Jdbc3KeyGenerator.INSTANCE)
                }
            }
        }
    }

    fun ensureInsertStatementPrepared() {
        if (!sessionFactory.configuration.hasStatement(getInsertStatementId())) {
            prepareInsertStatement();
        }
    }

    fun getDeleteStatementId() = "${id}!DYN_DELETE"

    /**
     * 准备初始化插入方法
     */
    fun prepareDeleteStatement() {
        prepareMybatisStatements(sessionFactory!!.configuration, getDeleteStatementId(), SqlProvider(this), "delete", SqlCommandType.DELETE){}
    }

    fun ensureDeleteStatementPrepared() {
        if (!sessionFactory.configuration.hasStatement(getDeleteStatementId())) {
            prepareDeleteStatement()
        }
    }

    fun getLoadStatementId() = "${id}!DYN_LOAD"
    fun ensureLoadStatementPrepared() {
        if (!sessionFactory.configuration.hasStatement(getLoadStatementId())) {
            prepareLoadStatement()
        }
    }

    fun prepareLoadStatement() {
        val config = sessionFactory!!.configuration
        prepareMybatisStatements(config, getLoadStatementId(), SqlProvider(this), "load", SqlCommandType.SELECT) {
            it.resultMaps(
                listOf<ResultMap>(
                    ResultMap.Builder(config, getLoadStatementId() + "-Inline", MutableMap::class.java, mutableListOf()).build()
                )
            )
        }
    }
}