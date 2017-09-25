package org.halo.thallo.mmr.core.service.impl;

import org.halo.thallo.mmr.core.impl.service.AttributeImpl;
import org.halo.thallo.mmr.core.impl.service.DataObjectImpl;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.types.BoolType;
import org.halo.thallo.mmr.core.model.types.LongType;
import org.halo.thallo.mmr.core.model.types.StringType;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

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


    private DataObject dataObject() {
        DataObjectImpl impl = new DataObjectImpl();
        Attribute id = new AttributeImpl();
        id.setName("id");
        id.setId("attr_01");
        id.setValueType(new LongType() {
        });

        Attribute name = new AttributeImpl();
        name.setName("name");
        name.setId("attr_02");
        id.setValueType(new StringType() {
        });

        Attribute locked = new AttributeImpl();
        locked.setName("locked");
        locked.setId("attr_03");
        id.setValueType(new BoolType() {
        });

        impl.setId("dataobject_id");
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
