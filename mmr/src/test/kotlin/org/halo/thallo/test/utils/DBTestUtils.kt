package org.halo.thallo.test.utils

import org.apache.commons.dbutils.QueryRunner
import org.apache.ibatis.session.SqlSession
import javax.sql.DataSource

const val TYPES_TABLE = "TABLE"

class DBTestUtils (var dataSource: DataSource) {

    val runner = QueryRunner(dataSource)

    fun isTableExists(tableName: String): Boolean {
        val conn = dataSource.connection
        val rs = conn.metaData
                .getTables(null, null, tableName.toUpperCase(), arrayOf(TYPES_TABLE))
        return rs.next()
    }

    fun columnValue(tableName: String, columnName:String, condition:String): Any? {
        var value:Any? = null
         runner.query("select ${columnName} from ${tableName} where ${condition}") {
            if (it.next()) {
                value = it.getObject(1)
            }
        }
        return value
    }
}

fun countRows(sqlSession:SqlSession, tableName:String, condition:String): Int {
    var count = 0
    val runner = QueryRunner()
    runner.query(sqlSession.connection,
            "select count(1) from ${tableName} where ${condition}") {
        count = if (it.next()) it.getInt(1)  else 0
    }
    return count
}