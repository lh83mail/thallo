package org.halo.thallo.mmr.core.impl.service

import com.alibaba.fastjson.JSONObject
import org.apache.ibatis.jdbc.SQL
import org.halo.thallo.mmr.core.impl.config.AbstractModel
import org.halo.thallo.mmr.core.mapper.DataStoreMapper
import org.halo.thallo.mmr.core.model.DataSchema
import org.halo.thallo.mmr.core.model.DataStore
import org.halo.thallo.mmr.core.model.Model
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import java.util.HashMap

class DataStoreImpl2
    constructor(dataObject: DataSchema , dataStoreMapper: DataStoreMapper)  : DataStore {

    private var id: String? = null
    private var name: String? = null
    private var description: String? = null

    override fun getId(): String? {
        return id
    }
    override fun setId(id: String?) {
       this.id = id
    }

    override fun getName(): String? {
        return this.name
    }
    override fun setName(name: String?) {
        this.name = name
    }

    private val dataObject: DataSchema? = null
    private val dataStoreMapper: DataStoreMapper? = null
    private var jdbcTemplate: NamedParameterJdbcTemplate? = null

    override fun newData(): MutableMap<String, *> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun init() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSchema(): DataSchema {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun persist(dataSet: MutableMap<String, *>?): MutableMap<String, *> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun persist(): DataSchema {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getDescription(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDescription(description: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clone(): Model {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun load(id: String?): MutableMap<String, *> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun load(): DataSchema {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}