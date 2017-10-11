package org.halo.thallo.mmr.core.service.impl;

import org.apache.commons.lang.StringUtils;
import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.DataStore;
import org.halo.thallo.mmr.core.service.DataObjectService;
import org.halo.thallo.mmr.core.service.DataStoreManager;
import org.halo.thallo.mmr.core.service.MMRException;

import java.util.List;

/**
 * Created by lihong on 17-10-11.
 */
public class DataStoreManagerImpl implements DataStoreManager {
    private DataObjectService dataObjectService;
    private DataStoreMapper dataStoreMapper;

    @Override
    public DataStore createStore(String oid) throws MMRException {
        DataObject dataObject = dataObjectService.getDataObject(oid);
        if (dataObject == null) {
            throw new MMRException(String.format("找不到对象%s定义", oid));
        }
        StringBuffer buf = generateCreateTableSql(dataObject);
        dataStoreMapper.execute(buf.toString());
        return null;
    }


    @Override
    public void emptyStore(String oid) {

    }

    @Override
    public void dropStore(String oid) {

    }

    @Override
    public DataStore getDataStore(String oid) {
        return null;
    }


    private StringBuffer generateCreateTableSql(DataObject dataObject) {
        StringBuffer buf = new StringBuffer();
        buf.append("create table ")
                .append(dataObject.getName())
                .append("(");

        dataObject.getAttributes().forEach(a -> {
            buf.append(a.getDBColumnDefinition());
        });

        List<Attribute> attributeList = dataObject.getIdAttributes();
        String[] names = new String[attributeList.size()];
        for (int i = 0; i < names.length; i++) {
            names[i] = attributeList.get(i).getName();
        }

        buf.append(" primary key (")
                .append(StringUtils.join(names, ","))
                .append(")");
        buf.append(")");
        return buf;
    }
}
