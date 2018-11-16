//package org.halo.thallo.mmr.core.impl.service;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.ibatis.jdbc.SQL;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.session.SqlSession;
//
//import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
//import org.halo.thallo.mmr.core.model.Attribute;
//import org.halo.thallo.mmr.core.model.DataSchema;
//import org.halo.thallo.mmr.core.model.DataStore;
//import org.halo.thallo.mmr.core.model.Model;
//import org.halo.thallo.mmr.core.runtime.Filter;
//import org.halo.thallo.mmr.core.runtime.PageRequest;
//import org.halo.thallo.mmr.core.runtime.PagedData;
//import org.halo.thallo.mmr.core.runtime.Sort;
//import org.halo.thallo.mmr.core.service.MMRException;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by lihong on 17-10-11.
// */
//public class DataStoreImpl.kt implements DataStore {
//    private DataSchema dataObject;
//    private DataStoreMapper dataStoreMapper;
//    private NamedParameterJdbcTemplate jdbcTemplate;
//
//    public DataStoreImpl.kt(DataSchema dataObject, DataStoreMapper dataStoreMapper) {
//        this.dataObject = dataObject;
//        this.dataStoreMapper = dataStoreMapper;
//    }
//
//    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    /**
//     * 初始化表空间
//     * @throws MMRException
//     */
//    @Override
//    public void init() throws MMRException {
//    }
//
//    @Override
//    public void pure() throws MMRException {
//
//    }
//
//    @Override
//    public DataSchema getSchema() throws MMRException {
//        return null;
//    }
//
//    @Override
//    public Map<String, ?> persist(Map<String, ?> dataSet) {
//        return null;
//    }
//
//    @Override
//    public Map<String, ?> load(String id) {
//        return null;
//    }
//
//    @Override
//    public void delete(String id) {
//
//    }
//
//    @Override
//    public Map<String, ?> newData() {
//        return null;
//    }
//
//    @Override
//    public String getId() {
//        return null;
//    }
//
//    @Override
//    public void setId(String id) {
//
//    }
//
//    @Override
//    public String getName() {
//        return null;
//    }
//
//    @Override
//    public void setName(String name) {
//
//    }
//
//    @Override
//    public String getDescription() {
//        return null;
//    }
//
//    @Override
//    public void setDescription(String description) {
//
//    }
//
//    @Override
//    public Model clone() throws CloneNotSupportedException {
//        return null;
//    }
//
//    public boolean create() {
//        StringBuffer buf = generateCreateTableSql(dataObject);
//        dataStoreMapper.execute(buf.toString());
//        return true;
//    }
//
//
//    public boolean drop() {
//        StringBuffer buf = generateDropTableSql(dataObject);
//        dataStoreMapper.execute(buf.toString());
//        return true;
//    }
//
//    /**
//     * 根据ID删除对象
//     * @throws MMRException
//     */
//    public void delete() throws MMRException {
//        List<Attribute>  idList = this.dataObject.getIdAttributes();
//        HashMap<String, Object> params = new HashMap<>();
//
//        SQL sql = new SQL(){{
//            DELETE_FROM(dataObject.getName());
//            for (Attribute id : idList) {
//                WHERE(id.getName() + " = :" + id.getName());
//                params.put(id.getName(), id.getValue());
//            }
//        }};
//
//        jdbcTemplate.update(sql.toString(), params);
//    }
//
//
//    @Override
//    public DataSchema load() throws MMRException {
//        List<Attribute>  idList = this.dataObject.getIdAttributes();
//        HashMap<String, Object> params = new HashMap<>();
//
//        SQL sql = new SQL(){{
//            dataObject.getAttributes().forEach(attribute -> {
//                SELECT(attribute.getName());
//            });
//            FROM(dataObject.getName());
//            for (Attribute id : idList) {
//                WHERE(id.getName() + " = :" + id.getName());
//                params.put(id.getName(), id.getValue());
//            }
//        }};
//        Map<String, Object> result = jdbcTemplate.queryForMap(sql.toString(), params);
//        return setData(result);
//    }
//
//    private DataSchema setData(Map<String, ?> values) throws MMRException {
//        for (Attribute a : dataObject.getAttributes()) {
//            a.setValue(values.get(a.getName()));
//        }
//        return dataObject;
//    }
//
//    /**
//     * 修改属性值
//     * @param values
//     * @return
//     * @throws MMRException
//     */
//    public DataSchema update(Map<String, Object> values) throws MMRException {
//        assert dataObject != null;
//
//        SQL sql = new SQL(){{
//            UPDATE(dataObject.getName());
//            Iterable<Attribute> attributes = dataObject.getAttributes();
//            attributes.forEach(attr -> {
//                if (attr.isUpdateable() && values.containsKey(attr.getName())) {
//                    SET(attr.getName() + "= :" +attr.getName());
//                }
//            });
//        }};
//
//        jdbcTemplate.update(sql.toString(), new MapSqlParameterSource(values));
//        return setData(values);
//    }
//
//    @Override
//    public DataSchema persist() throws MMRException {
//        assert dataObject != null;
//
//        Map<String, Object> values = new HashMap<>();
//        SQL sql = new SQL(){{
//            INSERT_INTO(dataObject.getName());
//            Iterable<Attribute> attributes = dataObject.getAttributes();
//            attributes.forEach(attr -> {
//                if (attr.isInsertable() || attr.isUpdateable()) {
//                    VALUES(attr.getName(), ":" + attr.getName());
//                    values.put(attr.getName(), attr.getValue());
//                }
//            });
//        }};
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        int result = jdbcTemplate.update(sql.toString(), new MapSqlParameterSource(values), keyHolder);
//
//        if (result > 0) {
//            List<Attribute> idAttributes = dataObject.getIdAttributes();
//            Map<String, Object> keys = keyHolder.getKeys();
//            if (keys != null) {
//                idAttributes.forEach(a -> {
//                    if (keys.containsKey(a.getName())) {
//                        a.setValue(keys.get(a.getName()));
//                    }
//                });
//            }
//        }
//
//        return dataObject;
//    }
//
//    public boolean empty() {
//        SqlSession sqlSession;
////        SqlMapper sqlMapper;
//        MappedStatement sm;
//        StringBuffer buf = generateEmptyTableSql(dataObject);
//        dataStoreMapper.execute(buf.toString());
//        return true;
//    }
//
//    private StringBuffer generateDropTableSql(DataSchema dataObject) {
//        return new StringBuffer("drop table ").append(dataObject.getName());
//    }
//
//    private StringBuffer generateEmptyTableSql(DataSchema dataObject) {
//        return new StringBuffer("truncate table ").append(dataObject.getName());
//    }
//
//    private StringBuffer generateCreateTableSql(DataSchema dataObject) {
//        StringBuffer buf = new StringBuffer();
//        buf.append("create table ")
//                .append(dataObject.getName())
//                .append("(");
//        DbDefintionMapper mapper = new DbDefintionMapper();
//
//        dataObject.getAttributes().forEach(a -> {
//            buf.append(mapper.columnDefintion(a));
//            buf.append(",");
//        });
//
//        List<Attribute> attributeList = dataObject.getIdAttributes();
//        String[] names = new String[attributeList.size()];
//        for (int i = 0; i < names.length; i++) {
//            names[i] = attributeList.get(i).getName();
//        }
//
//        buf.append(" primary key (")
//                .append(StringUtils.join(names, ","))
//                .append(")");
//        buf.append(")");
//        return buf;
//    }
//
//    public PagedData<DataSchema> filter(Filter filter, Sort sort, PageRequest pageRequest) throws MMRException {
//
//        HashMap<String, Object> params = new HashMap<>();
//        SQL sql = new SQL(){{
//            dataObject.getAttributes().forEach(attribute -> {
//                SELECT(attribute.getName());
//            });
//            FROM(dataObject.getName());
//            if (filter != null) {
//                filter.apply(this, params);
//            }
//
//            if (sort != null) {
//                sort.apply(this, params);
//            }
//
//        }};
//
//        PagedData<DataSchema> pagedData = new PagedData<>();
//
//        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql.toString(), params);
//        ArrayList<DataSchema> arrayList = new ArrayList<>();
//        if (result != null) {
//            for (int i = 0, sz = result.size(); i < sz; i++) {
//                arrayList.add(setData(result.get(i)));
//            }
//        }
//        pagedData.setData(result);
//
//        if (pageRequest != null) {
//            HashMap<String, Object> countParams = new HashMap<>();
//            SQL countSql = new SQL() {{
//                SELECT("count(1) as count");
//                FROM(dataObject.getName());
//                if (filter != null) {
//                    filter.apply(this, countParams);
//                }
//            }};
//            Number number = jdbcTemplate.queryForObject(countSql.toString(), countParams, Number.class);
//            pagedData.setTotalRecords(number.intValue());
//        }
//
//        return pagedData;
//    }
//}
