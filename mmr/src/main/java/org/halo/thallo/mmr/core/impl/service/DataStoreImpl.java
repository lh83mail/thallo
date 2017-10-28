package org.halo.thallo.mmr.core.impl.service;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.DataStore;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihong on 17-10-11.
 */
public class DataStoreImpl implements DataStore {
    private DataObject dataObject;
    private DataStoreMapper dataStoreMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;


    public DataStoreImpl(DataObject dataObject, DataStoreMapper dataStoreMapper) {
        this.dataObject = dataObject;
        this.dataStoreMapper = dataStoreMapper;
    }

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        assert dataObject != null;

        SQL sql = new SQL(){{
            INSERT_INTO(dataObject.getName());
            Iterable<Attribute> attributes = dataObject.getAttributes();
            attributes.forEach(attr -> {
                if (attr.isInsertable() && values.containsKey(attr.getName())) {
                    VALUES(attr.getName(), ":" + attr.getName());
                }
            });
        }};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update(sql.toString(), new MapSqlParameterSource(values), keyHolder);

        if (result > 0) {

        }
        return null;
    }


    @Override
    public boolean empty() {
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
        DbDefintionMapper mapper = new DbDefintionMapper();

        dataObject.getAttributes().forEach(a -> {
            buf.append(mapper.columnDefintion(a));
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
