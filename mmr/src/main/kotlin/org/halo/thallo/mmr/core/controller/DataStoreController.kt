package org.halo.thallo.mmr.core.controller

import org.halo.thallo.mmr.core.service.DataStoreManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 存储空间管理
 */
@RestController
@RequestMapping("/v1/data-store")
class DataStoreController {
    @Autowired
    lateinit var storeManager: DataStoreManager

    /**
     * 清空数据集中全部数据
     */
    fun emptyStore(@PathVariable id: String){}

    fun saveStore(@PathVariable id: String){}

    fun getStore(@PathVariable id: String) {}

    fun delStore(@PathVariable id: String){}

    /**
     * 初始化DataStore
     */
    fun initStore(@PathVariable id: String) {}

    /**
     * 列出全部数据集
     */
    @GetMapping
    fun listStores(){}
}