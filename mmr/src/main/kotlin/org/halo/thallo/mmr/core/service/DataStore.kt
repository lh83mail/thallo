package org.halo.thallo.mmr.core.service

/**
 * 数据集存放
 * Created by lihong on 17-10-10.
 */
interface DataStore {

    /**
     * 初始化数据存储空间
     * @throws MMRException
     */
    @Throws(MMRException::class)
    fun init()

    /**
     * 持久化数据集
     * @param dataSet
     * @return
     */
    fun persist(dataSet: Map<String, *>): Map<String, *>

    /**
     * 加载数据集
     */
    fun load(keys: Map<String, Any>): Map<String, *>

    /**
     * 删除指定数据
     */
    fun delete(keys: Map<String, Any>)
}
