package org.halo.thallo.mmr.core.model;

import java.util.Map;

/**
 * Created by lihong on 17-10-10.
 */
public interface DataStore {
    boolean create();
    boolean drop();
    boolean empty();

    DataObject persist(Map<String, Object> values);
}
