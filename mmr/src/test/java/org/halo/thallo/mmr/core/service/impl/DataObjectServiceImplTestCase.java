package org.halo.thallo.mmr.core.service.impl;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Map;

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
