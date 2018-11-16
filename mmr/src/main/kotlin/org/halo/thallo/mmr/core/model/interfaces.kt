package org.halo.thallo.mmr.core.model

val AUTO_INCREMENT   = object : ValueProvider { override fun getValue(): Any? = throw Error("这个类型不应该调用值") }
val NULL_VALUE_RPOVIDER = object : ValueProvider { override fun getValue(): Any? { return null } }

/**
 * 值生成器
 */
@FunctionalInterface
interface ValueProvider {
    /**
     * 获取值生成器
     */
    fun getValue(): Any?
}
