package org.halo.thallo.mmr.core.impl.service;

import org.halo.thallo.mmr.core.mapper.DataStoreMapper;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.DataStore;
import org.halo.thallo.mmr.core.service.DataObjectService;
import org.halo.thallo.mmr.core.service.DataStoreManager;
import org.halo.thallo.mmr.core.service.MMRException;

/**
 * Created by lihong on 17-10-11.
 */
public class DataStoreManagerImpl implements DataStoreManager {
    private DataObjectService dataObjectService;
    private DataStoreMapper dataStoreMapper;

    @Override
    public DataStore getDataStore(String oid) throws MMRException {
        DataObject dataObject = dataObjectService.getDataObject(oid);
        if (dataObject == null) {
            throw new MMRException(String.format("找不到对象%s定义", oid));
        }

        DataStoreImpl dataStore = new DataStoreImpl(dataObject, dataStoreMapper);
        return  dataStore;
    }


}
