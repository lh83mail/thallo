package org.halo.thallo.mmr.core.model


import org.halo.thallo.mmr.core.model.op.Operation

/**
 * Created by dell01 on 2017/8/14.
 */
interface Attribute : Model {

    var value: Any?

    /**
     * 数据类型
     */
    var type: ValueType

    val length: Int

    var isPrimary: Boolean

    var valueProvider: ValueProvider

    val updateAble: Boolean

    val insertable: Boolean
}
