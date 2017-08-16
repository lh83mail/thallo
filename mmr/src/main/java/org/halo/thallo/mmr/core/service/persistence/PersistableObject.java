package org.halo.thallo.mmr.core.service.persistence;

import java.io.Serializable;

/**
 * 可持久对象
 * Created by lihong on 17-8-15.
 */
public interface PersistableObject extends Serializable {
    String getId();
    Object toPersistenceObject();
}
