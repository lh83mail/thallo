package org.halo.thallo.mmr.core.model;

/**
 * Created by lihong on 17-10-10.
 */
public interface DataStore {
    boolean create();
    boolean drop();
    boolean empty();
}
