package org.halo.thallo.mmr.core.service;

import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.runtime.Filter;
import org.halo.thallo.mmr.core.runtime.PageRequest;
import org.halo.thallo.mmr.core.runtime.PagedData;
import org.halo.thallo.mmr.core.runtime.Sort;

/**
 * Created by lihong on 17-8-15.
 */
public interface DataObjectManager {
    DataObject save(DataObject dataObject) throws MMRException;
    DataObject load(DataObject dataObject) throws MMRException;
    PagedData<DataObject> filter(DataObject dataObject, Filter filter, Sort sort, PageRequest pageRequest) throws MMRException;

    DataObject createDataObject(String id);
    boolean deleteDataObject(DataObject dataObject);
    DataObject getDataObject(String id);



}
