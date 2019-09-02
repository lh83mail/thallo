package org.halo.thallo.mmr.core.service


/**
 * 数据管理器
 */
interface DataStoreManager {

    /**
     * 获取表存储空间
     * @param id
     * @return
     */
    fun getStore(id: String): DataStore

    fun get(store: DataStore): DataStore

    /**
     * 保存数据存储配置空间
     * @param id
     * @param store
     * @return
     */
    fun save(store: DataStore): DataStore

    fun update(store: DataStore): DataStore

    fun delete(store: DataStore)

    fun query(filter: Filter, pageRequest: PageRequest) : Page<DataStore>

}