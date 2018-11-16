package org.halo.thallo.mmr.core.impl.service

import org.apache.ibatis.session.SqlSessionFactory
import org.halo.thallo.mmr.core.model.AUTO_INCREMENT
import org.halo.thallo.mmr.core.model.ValueType


/**
 * 创建DataStoreImpl 表数据
 */
fun createDefaultDataStore(sessionFactory: SqlSessionFactory): DataStoreImpl {
    val id = AttributeImpl()
    id.name = "id"
    id.id = "attr_01"
    id.valueType = ValueType.LONG
    id.isInsertable = false
    id.isPrimary = true
    id.valueProvider = AUTO_INCREMENT

    val name = AttributeImpl()
    name.name = "name"
    name.id = "attr_02"
    name.valueType = ValueType.STRING

    val locked = AttributeImpl()
    locked.name = "locked"
    locked.id = "attr_03"
    locked.valueType = ValueType.BOOL

    val impl = DataStoreImpl(sessionFactory, null)
    impl.id = "dataobject_id"
    impl.name = "my_unit_test_tbl"
    impl.addAttributes(id, name, locked)

    return impl
}