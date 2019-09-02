package org.halo.thallo.mmr.core.service.impl.commands

import org.apache.ibatis.builder.SqlSourceBuilder
import org.apache.ibatis.jdbc.SQL
import org.apache.ibatis.mapping.SqlSource
import org.apache.ibatis.session.SqlSessionFactory
import org.halo.thallo.mmr.core.service.Command
import org.halo.thallo.mmr.core.service.View
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class QueryCommand(val view : View, val sesionFactory: SqlSessionFactory) : Command {



    override fun execute(requestData: Map<String, Any>, request: HttpServletRequest, response: HttpServletResponse): Any {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

        sesionFactory
                .openSession()
                .select("") {




                }
    }


    fun createSqlSource(): SqlSource? {

        val sqlSourceParser = SqlSourceBuilder(config)
        // create sql
        val sql = SQL()
        sql.FROM("aaa")
        sql.SELECT()
        sql.LEFT_OUTER_JOIN("")
        sql.WHERE("")
        sql.ORDER_BY("").AND().WHERE("","","")
                .OR().WHERE("","","","")
                .AND()
                .AND()
                .AND()
                .OR()
           "select name, value from aaa where id=:id and name like '%zhan%' "

        view.attributes.forEach {


        }

        sqlSourceParser.parse()

        val attrs = view.attributes
        attrs.forEach { attr -> }

        return sqlSourceParser.b
    }

}