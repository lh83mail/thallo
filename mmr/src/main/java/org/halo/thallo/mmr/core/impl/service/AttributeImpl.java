package org.halo.thallo.mmr.core.impl.service;

import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.ValueType;
import org.halo.thallo.mmr.core.model.op.Operation;

/**
 * Created by dell01 on 2017/9/25.
 */
public class AttributeImpl implements Attribute {
    private String id;
    private String name;
    private String description;
    private Object value;
    private boolean insertable = true;
    private boolean updateable = true;
    private ValueType valueType;
    private int length;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
