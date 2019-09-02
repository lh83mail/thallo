package org.halo.thallo.mmr.core.service.impl

import org.apache.ibatis.session.SqlSessionFactory
import org.halo.thallo.mmr.core.service.*



class DefaultDataStoreManager(val sessionFactory: SqlSessionFactory) : DataStoreManager {

    override fun getStore(id: String): DataStore {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun get(store: DataStore): DataStore {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(store: DataStore): DataStore {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(store: DataStore): DataStore {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(store: DataStore) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(filter: Filter, pageRequest: PageRequest): Page<DataStore> {
        var store : DataStore
        List<QueryField> fileds = filter.fileds()

        //


        this.sessionFactory.openSession().select("oid")


        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}