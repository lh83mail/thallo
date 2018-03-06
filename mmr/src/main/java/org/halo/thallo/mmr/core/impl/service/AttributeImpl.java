package org.halo.thallo.mmr.core.impl.service;

import com.alibaba.fastjson.JSONObject;
import org.halo.thallo.mmr.core.impl.config.AbstractModel;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.ValueType;
import org.halo.thallo.mmr.core.model.op.Operation;

/**
 * Created by dell01 on 2017/9/25.
 */
public class AttributeImpl extends AbstractModel implements Attribute {
    private Object value;
    private boolean insertable = true;
    private boolean updateable = true;
    private ValueType valueType;
    private int length;

    public AttributeImpl(JSONObject config) {
        super(config);

        this.setInsertable(config.containsKey("insertable")? config.getBooleanValue("insertable") : true);
        this.setUpdateable(config.containsKey("updateable")? config.getBooleanValue("updateable") : true);
        this.setValueType(config.containsKey("valueType")? ValueType.valueOf(config.getString("valueType")) : ValueType.STRING);
        this.setValue(config.get("value"));
        this.setLength(config.containsKey("length")? config.getIntValue("length") : 50);
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean isInsertable() {
        return insertable;
    }

    @Override
    public ValueType getValueType() {
        return valueType;
    }

    public void setInsertable(boolean insertable) {
        this.insertable = insertable;
    }

    @Override
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    @Override
    public Operation getOperation() {
        return null;
    }

    @Override
    public AttributeImpl clone() throws CloneNotSupportedException {
        return (AttributeImpl) super.clone();
    }

    @Override
    public boolean isUpdateable() {
        return updateable;
    }

    public void setUpdateable(boolean updateable) {
        this.updateable = updateable;
    }
}
