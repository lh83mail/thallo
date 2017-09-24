package org.halo.thallo.mmr.core.model;

import org.halo.thallo.mmr.core.service.persistence.PersistableObject;

import java.util.List;

/**
 * Created by dell01 on 2017/9/24.
 */
public interface DataObject {
    Schema getSchema();

    List<PersistableObject> toPersistableObjects();

    Object getValue(Attribute attribute);
}
