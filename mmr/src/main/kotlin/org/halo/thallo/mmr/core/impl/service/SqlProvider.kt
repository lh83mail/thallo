package org.halo.thallo.mmr.core.impl.service

import org.apache.ibatis.jdbc.SQL
import org.halo.thallo.mmr.core.model.DataSchema

class SqlProvider(val schema: DataSchema) {

    fun insert(param: Any?): String {
        val sql = object : SQL() {
            init {
                INSERT_INTO(schema.name)
                schema.attributes.forEach { attr ->
                    if (attr.isInsertable) {
                        VALUES(attr.name, "#{${attr.name}}")
                    }
                }
            }
        }
        return sql.toString()
    }

    fun delete(param: Map<String, Any>): String {
        val sql = object : SQL() {
            init {
                DELETE_FROM(schema.name)
                schema.idAttributes.forEach {
                    WHERE("${it.name} = #{${it.name}}")
                }
            }
        }
        return sql.toString()
    }

    fun load(param: Map<String, Any>): String {
        val sql = object : SQL() {
            init {
                SELECT("*")
                FROM(schema.name)
                schema.idAttributes.forEach {
                    WHERE("${it.name} = #{${it.name}}")
                }
            }
        }
        return sql.toString()
    }
}