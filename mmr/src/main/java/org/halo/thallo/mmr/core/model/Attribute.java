package org.halo.thallo.mmr.core.model;


/**
 * Created by dell01 on 2017/8/14.
 */
public interface Attribute extends Model {

    /**
     * 数据库列定义
     * @return
     */
    String getDBColumnDefinition();

    Object getValue();

    boolean isInsertable();

    void setValue(Object val);

    ValueType getValueType();

    void setValueType(ValueType valueType);
}
