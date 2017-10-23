package org.halo.thallo.mmr.core.config;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lihong on 17-10-23.
 */
public class MybatisConfiguration {

    @Autowired
    private SqlSession sqlSession;

//    @Bean
//    public SqlMapper sqlMapper() {
//        return new SqlMapper(sqlSession);
//    }
}
