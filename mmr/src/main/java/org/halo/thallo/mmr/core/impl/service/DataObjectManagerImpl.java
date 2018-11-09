package org.halo.thallo.mmr.core.impl.service;

import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
import org.halo.thallo.mmr.core.model.DataSchema;
import org.halo.thallo.mmr.core.runtime.Filter;
import org.halo.thallo.mmr.core.runtime.PageRequest;
import org.halo.thallo.mmr.core.runtime.PagedData;
import org.halo.thallo.mmr.core.runtime.Sort;
import org.halo.thallo.mmr.core.service.DataObjectManager;
import org.halo.thallo.mmr.core.service.MMRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DataObjectManagerImpl implements DataObjectManager {
    private DataStoreMapper dataStoreMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public DataSchema createDataObject(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public DataSchema save(DataSchema dataObject) throws MMRException {
        DataStoreImpl dataStore = new DataStoreImpl(dataObject, dataStoreMapper);
        dataStore.setJdbcTemplate(jdbcTemplate);
        return dataStore.persist();
    }

    @Override
    public DataSchema load(DataSchema dataObject) throws MMRException {
        DataStoreImpl dataStore = new DataStoreImpl(dataObject, dataStoreMapper);
        dataStore.setJdbcTemplate(jdbcTemplate);
        return dataStore.load();
    }

    @Override
    public boolean deleteDataObject(DataSchema dataObject) {
        return true;
    }

    @Override
    public DataSchema getDataObject(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PagedData<DataSchema> filter(DataSchema dataObject, Filter filter, Sort sort, PageRequest pageRequest) throws MMRException {
        DataStoreImpl dataStore = new DataStoreImpl(dataObject, dataStoreMapper);
        dataStore.setJdbcTemplate(jdbcTemplate);
        return dataStore.filter(filter, sort, pageRequest);
    }

    @Autowired
    public void setDataStoreMapper(DataStoreMapper dataStoreMapper) {
        this.dataStoreMapper = dataStoreMapper;
    }

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
