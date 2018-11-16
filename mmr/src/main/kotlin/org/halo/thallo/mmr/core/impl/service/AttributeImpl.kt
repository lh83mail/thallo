package org.halo.thallo.mmr.core.impl.service

import com.alibaba.fastjson.JSONObject
import org.halo.thallo.mmr.core.impl.config.AbstractModel
import org.halo.thallo.mmr.core.model.Attribute
import org.halo.thallo.mmr.core.model.NULL_VALUE_RPOVIDER
import org.halo.thallo.mmr.core.model.ValueType
import org.halo.thallo.mmr.core.model.op.Operation

/**
 * Created by dell01 on 2017/9/25.
 */
class AttributeImpl : AbstractModel, Attribute {
    override var value: Any? = null
    override var isInsertable = true
    override var isUpdateable = true
    override var valueType = ValueType.STRING
    override var length = 0
    override var isPrimary = false
    override var operation: Operation? = null
    override var valueProvider = NULL_VALUE_RPOVIDER

    constructor()

    constructor(config: JSONObject) : super(config) {
        this.isInsertable = if (config.containsKey("insertable")) config.getBooleanValue("insertable") else true
        this.isUpdateable = if (config.containsKey("updateable")) config.getBooleanValue("updateable") else true
        this.valueType = if (config.containsKey("valueType")) ValueType.valueOf(config.getString("valueType")) else ValueType.STRING
        this.value = config["value"]
        this.length = config.getOrDefault("length", 50 ) as Int
        this.isPrimary = config.getOrDefault("primary", false) as Boolean
    }

    @Throws(CloneNotSupportedException::class)
    override fun clone(): AttributeImpl {
        return super.clone() as AttributeImpl
    }
}
