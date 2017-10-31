package org.halo.thallo.mmr.core.service.impl;

import org.apache.ibatis.jdbc.SQL;
import org.halo.thallo.mmr.core.impl.service.AttributeImpl;
import org.halo.thallo.mmr.core.impl.service.DataObjectImpl;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.ValueType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by dell01 on 2017/9/25.
 */
public class DataObjectMapper {

    @Test
    public void toDataObject() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 12L);
        data.put("name", "UserName");
        data.put("locked", false);

        DataObject object = toDataObject(dataObject(), data);

        assertNotNull(object);

        SQL sql = new SQL(){{
            INSERT_INTO(object.getName());
            Iterable<Attribute> attributes = object.getAttributes();
            attributes.forEach(attr -> {
                if (attr.isInsertable()) {
                    VALUES(attr.getName(), "#{" + attr.getName() + "}");
                }
            });
        }};
        System.out.println(sql.toString());


        Iterable<Attribute> attributes = object.getAttributes();
        assertNotNull(attributes);

        Iterator<Attribute> iter = attributes.iterator();
        int i = 0;
        while(iter.hasNext()) {
            Attribute attribute = iter.next();
            if ("id".equals(attribute.getName())) {
                assertEquals(12L, attribute.getValue());
                i++;
            }
            if ("name".equals(attribute.getName())) {
                assertEquals("UserName", attribute.getValue());
                i++;
            }
            if ("locked".equals(attribute.getName())) {
                assertFalse((Boolean) attribute.getValue());
                i++;
            }
        }
        assertEquals(3, i);

        List<Attribute> idAttrs = object.getIdAttributes();
        assertNotNull(idAttrs);
        assertEquals(1, idAttrs.size());
        assertEquals("id", idAttrs.get(0).getName());
        assertEquals(12L, idAttrs.get(0).getValue());
    }

    @Test
    public void toDataMap() {
        DataObject dataObject = dataObject();
        dataObject.getAttributes().forEach(attr -> {
            if ("attr_01".equals(attr.getId())) {
                attr.setValue(1L);
            }
            if ("attr_02".equals(attr.getId())) {
                attr.setValue("YourName");
            }
            if ("attr_03".equals(attr.getId())) {
                attr.setValue(true);
            }
        });

        Map<String, Object> data = toDataMap(dataObject);
        assertNotNull(data);
        assertEquals(1L, data.get("id"));
        assertEquals("YourName", data.get("name"));
        assertEquals(true, data.get("locked"));
    }


    private DataObject dataObject() {
        DataObjectImpl impl = new DataObjectImpl();
        Attribute id = new AttributeImpl();
        id.setName("id");
        id.setId("attr_01");
        id.setValueType(ValueType.LONG);

        Attribute name = new AttributeImpl();
        name.setName("name");
        name.setId("attr_02");
        id.setValueType(ValueType.STRING);

        Attribute locked = new AttributeImpl();
        locked.setName("locked");
        locked.setId("attr_03");
        id.setValueType(ValueType.BOOL);

        impl.setId("dataobject_id");
        impl.setName("my_table");
        impl.addAttributes(id, name, locked);
        impl.setPrimaryAttributes(id);

        return impl;
    }

    public DataObject toDataObject(DataObject dataObject, Map data) {
        Iterable<Attribute> attributes = dataObject.getAttributes();
        attributes.forEach(attribute -> {
            Object val = data.get(attribute.getName());
            attribute.setValue(val);
        });
        return  dataObject;
    }

    public Map toDataMap(DataObject object) {
        Map<String, Object> data = new HashMap<>();
        Iterable<Attribute> attributes = object.getAttributes();
        attributes.forEach(attribute -> {
            data.put(attribute.getName(), attribute.getValue());
        });
        return data;
    }
}
