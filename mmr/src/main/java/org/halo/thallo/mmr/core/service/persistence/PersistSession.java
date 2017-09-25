package org.halo.thallo.mmr.core.service.persistence;

/**
 * 持久化服务
 * Created by lihong on 17-8-15.
 */
public interface PersistSession {
    Object get(String id);
    void flush();
    void close();
}
