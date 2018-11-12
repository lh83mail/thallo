package org.halo.thallo.mmr.core.service

import org.halo.thallo.mmr.core.model.DataStore

/**
 * 数据管理器
 */
interface DataStoreManager {

    /**
     * 获取表存储空间
     * @param id
     * @return
     */
    @Throws(MMRException::class)
    fun getDataStore(id: String): DataStore

    /**
     * 保存数据存储配置空间
     * @param id
     * @param store
     * @return
     */
    fun saveDataStore(id: String, store: DataStore): DataStore

    /**
     * 获取数据存储配置信息
     * @param id
     * @param cfg
     * @return
     */
    fun listDataStores(id: String, page: Int = 1,  pageSize: Int = 20): List<DataStore>
}