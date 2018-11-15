package org.halo.thallo.mmr.core.mybatis

import org.apache.ibatis.builder.annotation.ProviderSqlSource
import org.apache.ibatis.mapping.BoundSql
import org.apache.ibatis.mapping.SqlSource
import org.apache.ibatis.session.Configuration
import kotlin.concurrent.thread


/**
 * 配置Mybaits
 */
fun expandMybatisConfiguration(configuration: Configuration) {
    configuration.apply {

    }
}


class ProgamableSqlSource : ProviderSqlSource {
    constructor(config: Configuration?, provider: Any?):super(null,null) {

    }

}