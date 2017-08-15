package org.halo.thallo.mmr.core.service;

import org.halo.thallo.mmr.core.model.DataObject;

import java.util.List;

/**
 * Created by lihong on 17-8-15.
 */
public interface DataObjectService {

    DataObject createDataObject();
    DataObject saveDataObject();
    boolean deleteDataObject();
    DataObject getDataObject();
    List<DataObject> queryObject(Object filter, Object orderby, int page, int limit);
}
