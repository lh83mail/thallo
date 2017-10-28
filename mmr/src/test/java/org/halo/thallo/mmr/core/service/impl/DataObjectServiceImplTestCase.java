package org.halo.thallo.mmr.core.service.impl;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.DataStore;
import org.halo.thallo.mmr.core.service.DataStoreManager;
import org.halo.thallo.mmr.core.service.MMRException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.*;

import static org.junit.Assert.assertNotNull;

/**
 * Created by lihong on 17-8-16.
 * http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-test-autoconfigure/
 */
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:e2e/application-e2e.properties")
@MybatisTest
@EnableTransactionManagement
public class DataObjectServiceImplTestCase {

    @Autowired
    private  SqlSession sqlSession;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void stu() {
        assertNotNull(sqlSession);
        assertNotNull(jdbcTemplate);
        assertNotNull(namedParameterJdbcTemplate);
//        Configuration configuration = sqlSession.getConfiguration();
//
//        SqlSourceBuilder sqlSourceBuilder = new  SqlSourceBuilder(configuration);
//        SqlSource sqlSource =  sqlSourceBuilder.parse("insert into MyTest(id_, object_, attr_) values (?,?,?)",
//                Map.class,
//                null
//        );
//        MappedStatement ms = new MappedStatement.Builder(configuration, "myinsert", sqlSource, SqlCommandType.INSERT)
//                .build();
//        System.out.println(sqlSource);

//        List<Object> params = new ArrayList<>();
//        params.addAll();
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("attr", "Lily");
        paraMap.put("obj", "Lily@sina.com");
        paraMap.put("key", 1);
        namedParameterJdbcTemplate.update("insert into MyTest(id_, object_, attr_) values(:key, :obj, :attr)", paraMap);
        Object result = jdbcTemplate.queryForList("select * from MyTest");
        System.out.println(result);
    }

    @Test
    public void objectMapper() {


    }

    @Test
    public void test() {
        assertNotNull(sqlSession);
        assertNotNull(sqlSessionTemplate);
        // 多表关联的情况
        // 处理级联情况
        // 更新时需要比较每一个字段的操作
        // 对象根据ID决定处理方式,
        // 属性有前端指定操作方式

    }
   // C, U
    private void C_U() throws MMRException {
        // inser table
        DataStoreManager dataStoreManager = null;
        DataStore store = dataStoreManager.getDataStore("oid");
        Map<String, Object> values = new HashMap<>();
        DataObject dataObject = store.persist(values);
    }

    private void R_D() throws MMRException {
//        R, D
        DataStoreManager dataStoreManager = null;
        DataStore store = dataStoreManager.getDataStore("oid");
        Map<String, Object> values = new HashMap<>();
//        DataObject dataObject = store.load(values);
//        DataObject dataObject = store.delete(values);
    }
}
