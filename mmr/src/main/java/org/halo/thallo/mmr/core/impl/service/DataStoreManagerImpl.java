package org.halo.thallo.mmr.core.impl.service;

import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
import org.halo.thallo.mmr.core.model.DataSchema;
import org.halo.thallo.mmr.core.model.DataStore;
import org.halo.thallo.mmr.core.service.DataObjectManager;
import org.halo.thallo.mmr.core.service.DataStoreManager;
import org.halo.thallo.mmr.core.service.MMRException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lihong on 17-10-11.
 */
@Service
public class DataStoreManagerImpl implements DataStoreManager {
    private DataObjectManager dataObjectManager;
    private DataStoreMapper dataStoreMapper;

    @Override
    public DataStore getDataStore(String oid) throws MMRException {
        DataSchema dataObject = dataObjectManager.getDataObject(oid);
        if (dataObject == null) {
            throw new MMRException(String.format("找不到对象%s定义", oid));
        }

        DataStoreImpl dataStore = null;//new DataStoreImpl.kt(dataObject, dataStoreMapper);
        return  dataStore;
    }

    @NotNull
    @Override
    public DataStore saveDataStore(@NotNull String id, @NotNull DataStore store) {
        return null;
    }

    @NotNull
    @Override
    public List<DataStore> listDataStores(@NotNull String id, int page, int pageSize) {
        return null;
    }
}
