package org.halo.thallo.mmr.core.service.impl;

import org.halo.thallo.mmr.core.impl.service.AttributeImpl;
import org.halo.thallo.mmr.core.impl.service.DataObjectImpl;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.ValueType;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.TestCase.assertEquals;

/**
 * 数据对象测试用例
 * 数据定义
 * Created by lihong on 17-11-15.
 */
public class DataObjectTestCase {

    /**
     * 对象定义
     */
    @Test
    public void testDefinine() {
        DataObject dataObject = new DataObjectImpl();

        dataObject.setId(UUID.randomUUID().toString());
        dataObject.setName("testData");
        dataObject.setDescription("测试数据对象");

        Attribute id = new AttributeImpl();
        id.setId(UUID.randomUUID().toString());
        id.setName("ID");
        id.setDescription("唯一ID");
        id.setValueType(ValueType.LONG);

        Attribute name = new AttributeImpl();
        id.setId(UUID.randomUUID().toString());
        id.setName("name");
        id.setDescription("用户姓名");
        id.setValueType(ValueType.STRING);

        Attribute locked = new AttributeImpl();
        id.setId(UUID.randomUUID().toString());
        id.setName("locked");
        id.setDescription("已锁定");
        id.setValueType(ValueType.BOOL);

        dataObject.addAttributes(id, name, locked);

        assertEquals("testData", dataObject.getName());
    }
}
