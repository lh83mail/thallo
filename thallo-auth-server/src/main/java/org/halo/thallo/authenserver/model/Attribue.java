package org.halo.thallo.authenserver.model;

import java.io.Serializable;

/**
 * 属性定义
 * Created by lihong on 17-4-15.
 */
public class Attribue implements Serializable {
    private String id;
    private String name;
    private String dataType;
    private Object value;
    private String description;

    public Attribue() {
    }

    public Attribue(String id, String name, String dataType, Object value, String description) {
        this.id = id;
        this.name = name;
        this.dataType = dataType;
        this.value = value;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
