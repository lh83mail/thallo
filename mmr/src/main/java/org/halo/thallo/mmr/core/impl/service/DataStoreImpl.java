package org.halo.thallo.mmr.core.impl.service;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.SqlSession;
import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.DataStore;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        StringBuffer buf = generateDropTableSql(dataObject);
        dataStoreMapper.execute(buf.toString());
        return true;
    }


    @Override
    public DataObject persist(Map<String, Object> values) {
        dataStoreMapper.insert(this.dataObject, values);
        return null;
    }

    @Override
    public boolean empty() {
        SqlSession sqlSession;
//        SqlMapper sqlMapper;
        MappedStatement sm;
        StringBuffer buf = generateEmptyTableSql(dataObject);
        dataStoreMapper.execute(buf.toString());
        return true;
    }

    private StringBuffer generateDropTableSql(DataObject dataObject) {
        return new StringBuffer("drop table ").append(dataObject.getName());
    }

    private StringBuffer generateEmptyTableSql(DataObject dataObject) {
        return new StringBuffer("truncate table ").append(dataObject.getName());
    }

    private StringBuffer generateCreateTableSql(DataObject dataObject) {
        StringBuffer buf = new StringBuffer();
        buf.append("create table ")
                .append(dataObject.getName())
                .append("(");

        dataObject.getAttributes().forEach(a -> {
            buf.append(a.getDBColumnDefinition());
            buf.append(",");
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
