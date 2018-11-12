package org.halo.thallo.mmr.core.service;

import org.halo.thallo.mmr.core.model.DataSchema;
import org.halo.thallo.mmr.core.runtime.Filter;
import org.halo.thallo.mmr.core.runtime.PageRequest;
import org.halo.thallo.mmr.core.runtime.PagedData;
import org.halo.thallo.mmr.core.runtime.Sort;

/**
 * Created by lihong on 17-8-15.
 */
public interface DataObjectManager {
    DataSchema save(DataSchema dataObject) throws MMRException;
    DataSchema load(DataSchema dataObject) throws MMRException;
    PagedData<DataSchema> filter(DataSchema dataObject, Filter filter, Sort sort, PageRequest pageRequest) throws MMRException;

    DataSchema createDataObject(String id);
    boolean deleteDataObject(DataSchema dataObject);
    DataSchema getDataObject(String id);



}
