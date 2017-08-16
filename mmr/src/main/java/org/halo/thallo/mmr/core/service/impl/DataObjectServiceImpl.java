package org.halo.thallo.mmr.core.service.impl;

import org.halo.thallo.mmr.core.impl.entity.DataObjectEntity;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.service.DataObjectService;
import org.halo.thallo.mmr.core.service.persistence.PersistSession;

import java.util.List;

/**
 * Created by lihong on 17-8-16.
 */
public class DataObjectServiceImpl implements DataObjectService {
    private PersistSession persistSession;

    @Override
    public DataObject createDataObject(String id) {
        DataObjectEntity data = (DataObjectEntity) persistSession.get(id);
        return data;
    }

    @Override
    public DataObject saveDataObject(DataObject dataObject) {
        return null;
    }

    @Override
    public boolean deleteDataObject() {
        return false;
    }

    @Override
    public DataObject getDataObject() {
        return null;
    }

    @Override
    public List<DataObject> queryObject(Object filter, Object orderby, int page, int limit) {
        return null;
    }

    public void setPersistSession(PersistSession persistSession) {
        this.persistSession = persistSession;
    }
}
