package org.halo.thallo.mmr.core.service;

import org.halo.thallo.mmr.core.model.DataStore;

/**
 * 数据存储空间管理服务
 * Created by lihong on 17-10-10.
 */
public interface DataStoreManager {

    /**
     * 创建DataObject的数据对象存储空间
     * @param oid
     */
    DataStore createStore(String oid) throws MMRException;

    /**
     * 清空数据对象存储空间
     * @param oid DataObject oid
     */
    void emptyStore(String oid);

    /**
     * 删除数据对象存储空间
     * @param oid
     */
    void dropStore(String oid);

    /**
     * 获取表存储空间
     * @param oid
     * @return
     */
    DataStore getDataStore(String oid);
}
