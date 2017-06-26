package org.halo.thallo.authenserver.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lihong on 17-4-15.
 */
public class Schema implements Serializable {
    private String id;
    private List<Attribute> attributes;
    private String descreption;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }
}
