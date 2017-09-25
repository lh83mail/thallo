package org.halo.thallo.mmr.core.service.impl;

import org.halo.thallo.mmr.core.impl.service.DataStoreImpl;
import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.DataStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.w3c.dom.Attr;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by lihong on 17-10-17.
 */
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:e2e/application-e2e.properties")
@MybatisTest
@EnableTransactionManagement
public class DataStoreImplTestCase {
    @Autowired
    private DataStoreMapper dataStoreMapper;


    @Test
    public void testDataStoreCreate()  {
        Attribute idAttr = mock(Attribute.class);
        when(idAttr.getDBColumnDefinition()).thenReturn("id_ bigint");
        when(idAttr.getName()).thenReturn("id_");

        Attribute nameAttr = mock(Attribute.class);
        when(nameAttr.getName()).thenReturn("name_");
        when(nameAttr.getDBColumnDefinition()).thenReturn("name_ varchar(50)");

        DataObject dataObject = mock(DataObject.class);
        when(dataObject.getName()).thenReturn("test_tbl");
        when(dataObject.getAttributes())
                .thenReturn(Arrays.asList(idAttr, nameAttr));
        when(dataObject.getIdAttributes()).thenReturn(Arrays.asList(idAttr));

        DataStore impl = new DataStoreImpl(dataObject, dataStoreMapper);
        boolean resulst = impl.create();
        assertTrue(resulst);
    }

}
