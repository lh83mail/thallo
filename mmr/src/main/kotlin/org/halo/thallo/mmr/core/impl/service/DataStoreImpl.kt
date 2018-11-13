package org.halo.thallo.mmr.core.impl.service

import com.alibaba.fastjson.JSONObject
import org.halo.thallo.mmr.core.impl.config.AbstractModel
import org.halo.thallo.mmr.core.mapper.DataStoreMapper
import org.halo.thallo.mmr.core.model.Attribute
import org.halo.thallo.mmr.core.model.DataSchema
import org.halo.thallo.mmr.core.model.DataStore

class DataStoreImpl (config: JSONObject?) : AbstractModel(config), DataSchema, DataStore {

    /**
     * 已经初始化
     */
    var initialized = false
        private set

    lateinit var dataStoreMapper: DataStoreMapper

    /**
     * 列定义映射
     */
    var dbDefintionMapper = DbDefintionMapper()
    /**
     * 所有属性列表
     */
    var attributes: MutableList<Attribute>? = null


    constructor(): this(null)

    init {
        if (config != null) {
            if (config.containsKey("attributes")) {
                val array = config.getJSONArray("attributes")
                array.forEach { arr -> this.addAttributes(AttributeImpl(arr as JSONObject)) }
            }
        }
    }

    /**
     * 获取属性列表
     */
    override fun getAttributes(): Iterable<Attribute> {
        return if (this.attributes == null) {
            listOf<Attribute>()
        } else {
            this.attributes!!.toList()
        }
    }

    /**
     * 添加属性列表
     */
    override fun addAttributes(vararg attributes: Attribute) {
        if (attributes == null) return

        if (this.attributes == null) {
            this.attributes = ArrayList()
        }

        for (attribute in attributes) {
            this.attributes!!.add(attribute)
        }
    }

    /**
     * 复制对象
     */
    @Throws(CloneNotSupportedException::class)
    override fun clone(): DataStoreImpl {
        val impl = super.clone() as DataStoreImpl
        if (attributes != null) {
            val templst = ArrayList<Attribute>()
            for (a in attributes!!) {
                templst.add(a.clone() as Attribute)
            }
            impl.attributes = templst
        }
        return impl
    }

    /**
     * 获取标识属性
     */
    override fun getIdAttributes(): List<Attribute> {
        return if (attributes == null) {
            emptyList()
        } else {
            attributes!!.filter { it.isPrimary }
        }
    }

    /**
     * 创建数据表结构
     */
    override fun init() {
        this.dataStoreMapper.execute(generateCreateTableSql().toString())
        this.initialized = true
        this.dataStoreMapper.updateDataStore(this)
    }

    private fun generateCreateTableSql(): StringBuffer {
        val buf = StringBuffer("create table ${this.name} (")
        this.attributes!!.forEach {
            buf.append(dbDefintionMapper.columnDefintion(it))
                .append(",");
        }

        val names = this.idAttributes.map { it.name }
        buf.append(" primary key (${names.joinToString(",")})")
        buf.append(")")
        return buf
    }

    /**
     * 持久化数据到DataStore中
     */
    override fun persist(data: Map<String, *>): Map<String, *> {
        return this.dataStoreMapper.insert(this, data)
    }

    override fun newData(): MutableMap<String, *> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun persist(): DataSchema {
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