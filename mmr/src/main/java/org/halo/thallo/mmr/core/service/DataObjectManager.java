package org.halo.thallo.mmr.core.service;

import org.halo.thallo.mmr.core.model.DataObject;

import java.util.List;

/**
 * Created by lihong on 17-8-15.
 */
public interface DataObjectManager {
    DataObject createDataObject(String id);
    DataObject saveDataObject(DataObject dataObject);
    boolean deleteDataObject(DataObject dataObject);
    DataObject getDataObject(String id);
    List<DataObject> queryObject(Object filter, Object orderby, int page, int limit);
}
