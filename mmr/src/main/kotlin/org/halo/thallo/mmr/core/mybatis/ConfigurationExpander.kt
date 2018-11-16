package org.halo.thallo.mmr.core.mybatis

import org.apache.ibatis.builder.SqlSourceBuilder
import org.apache.ibatis.mapping.BoundSql
import org.apache.ibatis.mapping.MappedStatement
import org.apache.ibatis.mapping.SqlCommandType
import org.apache.ibatis.mapping.SqlSource
import org.apache.ibatis.session.Configuration
import org.halo.thallo.mmr.core.service.MMRException
import java.lang.reflect.Method
import java.util.*

/**
 * 动态创建一个Statement 并添加到可config的空间中
 */
fun prepareMybatisStatements(
        config: Configuration, id:String, provider: Any, method: String, type: SqlCommandType,
        postProcessor: (builder: MappedStatement.Builder) -> Unit) {
    val sqlSource: SqlSource = ObjectSqlSource(config, provider, method )
    val builder = MappedStatement.Builder(config, id, sqlSource, type)
    postProcessor(builder)
    config.addMappedStatement(builder.build())
}

/**
 * 通过对象生成SQLSource
 */
class ObjectSqlSource(val config: Configuration?, val provider: Any, val method: String ) : SqlSource {

    private var providerMethod: Method? = null

    init {
        providerMethod = provider.javaClass.methods.first { it.name == method }
        if (providerMethod == null) {
            throw MMRException("必须指定生成sql的方法")
        }
    }

    override fun getBoundSql(parameterObject: Any?): BoundSql {
        val sqlSource = createSqlSource(parameterObject)
        return sqlSource.getBoundSql(parameterObject)
    }

    fun createSqlSource(parameterObject: Any?): SqlSource {
        val sqlSourceParser = SqlSourceBuilder(config)
        var sql = providerMethod?.let {
            val parameterTypes = it.getParameterTypes()

            (if (parameterTypes.size == 0) {
                it.invoke(provider)
            } else if (parameterTypes.size == 1) {
                it.invoke(provider, parameterObject)
            } else {

            }) as String
        }
        val parameterType = if (parameterObject == null) Any::class.java else parameterObject.javaClass
        return sqlSourceParser.parse(sql, parameterType, HashMap())
    }

}