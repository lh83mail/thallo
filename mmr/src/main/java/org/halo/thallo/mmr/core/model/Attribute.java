package org.halo.thallo.mmr.core.model;


import org.halo.thallo.mmr.core.model.op.Operation;

/**
 * Created by dell01 on 2017/8/14.
 */
public interface Attribute extends Model {

    Object getValue();

    boolean isInsertable();

    void setValue(Object val);

    ValueType getValueType();

    void setValueType(ValueType valueType);

    /**
     * 对属性的操作
     * @return
     */
    Operation getOperation();
}
