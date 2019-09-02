package org.halo.thallo.mmr.core.model.schema

/**
 * 模式定义
 */

interface Schema {
    var id: String
    var name: String
    var physicalName: String
    var description: String?
    fun clone(): Schema
}

/**
 * 属性
 */
interface Attribute: Schema {

}

interface DataShip {
    var target: DataSchema
    var joins: Array<Attribute>
}

/**
 * 数据对象接口
 */
interface DataSchema : Schema {
    var attributes: Array<Attribute>

    var ships: Array<DataShip>
}

//save one


interface  DataObject {
    val schema: DataSchema
    fun toPresitiObject(): Map<String, Object>
}




fun main() {
    val s:DataSchema? = null

}




{
    "id":"aaa",
    "attributes": [
        {},{}
    ],
    "primary": [
        "id":"",
        "bid": "String"
    ],
    "relations": [
        {
            "src": "aaa.id",
            "target": "bbb.aid",
            "join": "left join",
            "logic": "="
        },
        {
            "src": "aaa.id",
            "target": "bbb.aid",
            "join": "left join",
        },
        {
            "src": "aaa.id",
            "target": "bbb.aid",
            "join": "left join",
        },
    ]
}

//    //
//    select
//     count(a.xx)
//     a.aaa as a_aa, b.aaa as b_aa , c.aaa as c_aa
//    from "aaa"
//    left join "bbb" on "aaa.id" = "bbb.aid" and "aaa.age" > 12
//    left join "cccc" on "bbb.id" = "ccc.id"
//    where
//        aaa.name like '%helo%'
//        and (aa.age betwen 12 and 33 or aa.age between 55 and 66)
//    group by
//      a.aaa, b.aaa, c.aaa
//    order by
//        aaa.id asc,
//        aaa.createTime desc