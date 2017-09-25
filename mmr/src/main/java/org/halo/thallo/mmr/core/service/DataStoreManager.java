package org.halo.thallo.mmr.core.service;

import org.halo.thallo.mmr.core.model.DataStore;

/**
 * 数据存储空间管理服务
 * Created by lihong on 17-10-10.
 */
public interface DataStoreManager {

    /**
     * 获取表存储空间
     * @param oid
     * @return
     */
    DataStore getDataStore(String oid) throws MMRException;
}
