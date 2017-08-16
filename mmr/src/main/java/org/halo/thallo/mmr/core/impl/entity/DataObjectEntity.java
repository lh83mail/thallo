package org.halo.thallo.mmr.core.impl.entity;

import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.service.persistence.PersistableObject;

/**
 * Created by lihong on 17-8-16.
 */
public class DataObjectEntity extends DataObject implements PersistableObject {

    @Override
    public Object toPersistenceObject() {
        return null;
    }
}
