package org.halo.thallo.mmr.core.service.impl;

import org.halo.thallo.mmr.core.impl.service.AttributeImpl;
import org.halo.thallo.mmr.core.impl.service.DataObjectImpl;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;

import static org.halo.thallo.mmr.core.model.ValueType.*;

/**
 * Created by dell01 on 2017/10/28.
 */
public class Util {

    public static DataObject createUnitTestDataObject() {
        DataObjectImpl impl = new DataObjectImpl();
        Attribute id = new AttributeImpl();
        id.setName("id");
        id.setId("attr_01");
        id.setValueType(LONG);
        id.setPrimary(true);

        Attribute name = new AttributeImpl();
        name.setName("name");
        name.setId("attr_02");
        name.setValueType(STRING);

        Attribute locked = new AttributeImpl();
        locked.setName("locked");
        locked.setId("attr_03");
        locked.setValueType(BOOL);

        impl.setId("dataobject_id");
        impl.setName("my_unit_test_tbl");
        impl.addAttributes(id, name, locked);

        return impl;
    }
}
