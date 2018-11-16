package org.halo.thallo.mmr.core.model


import org.halo.thallo.mmr.core.model.op.Operation

/**
 * Created by dell01 on 2017/8/14.
 */
interface Attribute : Model {

    var value: Any?

    val isInsertable: Boolean

    var valueType: ValueType

    /**
     * 对属性的操作
     * @return
     */
    val operation: Operation?

    val length: Int

    val isUpdateable: Boolean

    var isPrimary: Boolean

    var valueProvider: ValueProvider
}
