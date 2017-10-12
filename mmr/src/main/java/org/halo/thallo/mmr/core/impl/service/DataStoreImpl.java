package org.halo.thallo.mmr.core.impl.service;

import org.apache.commons.lang.StringUtils;
import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.DataStore;

import java.util.List;

/**
 * Created by lihong on 17-10-11.
 */
public class DataStoreImpl implements DataStore {
    private DataObject dataObject;
    private DataStoreMapper dataStoreMapper;

    public DataStoreImpl(DataObject dataObject, DataStoreMapper dataStoreMapper) {
        this.dataObject = dataObject;
        this.dataStoreMapper = dataStoreMapper;
    }

    @Override
    public boolean create() {
        StringBuffer buf = generateCreateTableSql(dataObject);
        dataStoreMapper.execute(buf.toString());
        return true;
    }

    @Override
    public boolean drop() {
        return false;
    }

    @Override
    public boolean empty() {
        return false;
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
