package org.halo.thallo.mmr.core.impl.service

import com.alibaba.fastjson.JSONObject
import org.halo.thallo.mmr.core.impl.config.AbstractModel
import org.halo.thallo.mmr.core.mapper.DataStoreMapper
import org.halo.thallo.mmr.core.model.DataSchema
import org.halo.thallo.mmr.core.model.DataStore
import org.halo.thallo.mmr.core.model.Model
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

class DataStoreImpl (config: JSONObject?) : AbstractModel(config), DataStore {

    /**
     * 已经初始化
     */
    var initialized = false
        private set(value) {
            this.initialized = value
        }


    private lateinit var dataObject: DataSchema
    @Autowired
    private lateinit var jdbcTemplate: NamedParameterJdbcTemplate


    /**
     * 创建数据表结构
     */
    override fun init() {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.


    }

    private fun generateCreateTableSql(): StringBuffer {

        val buf = StringBuffer("create table ${this.id} (")
        val mapper =  DbDefintionMapper();
        this.dataObject.attributes.forEach {
            buf.append(mapper.columnDefintion(it))
                .append(",");
        }

        val names = this.dataObject.idAttributes.map { it.name }
        buf.append(" primary key (${names.joinToString(",")})")
        buf.append(")")
        return buf
    }




//    private StringBuffer generateCreateTableSql(DataSchema dataObject) {
//        StringBuffer buf = new StringBuffer();
//        buf.append("create table ")
//                .append(dataObject.getName())
//                .append("(");
//        DbDefintionMapper mapper = new DbDefintionMapper();
//
//        dataObject.getAttributes().forEach(a -> {
//            buf.append(mapper.columnDefintion(a));
//            buf.append(",");
//        });
//
//        List<Attribute> attributeList = dataObject.getIdAttributes();
//        String[] names = new String[attributeList.size()];
//        for (int i = 0; i < names.length; i++) {
//            names[i] = attributeList.get(i).getName();
//        }
//
//        buf.append(" primary key (")
//                .append(StringUtils.join(names, ","))
//                .append(")");
//        buf.append(")");
//        return buf;
//    }

    override fun newData(): MutableMap<String, *> {


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

fun main(args: Array<String>) {
    val arr = arrayOf("helo","GO",1,3)
    println(arr.joinToString(","))
}