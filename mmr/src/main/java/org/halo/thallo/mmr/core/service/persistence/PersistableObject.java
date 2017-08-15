package org.halo.thallo.mmr.core.service.persistence;

/**
 * 可持久对象
 * Created by lihong on 17-8-15.
 */
public interface PersistableObject {
    String getId();
    Object toPersistenceObject();
}
