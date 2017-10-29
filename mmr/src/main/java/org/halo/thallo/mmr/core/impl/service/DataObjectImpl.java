package org.halo.thallo.mmr.core.impl.service;

import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell01 on 2017/9/25.
 */
public class DataObjectImpl implements DataObject {
    private String id;
    private String name;
    private String description;
    private List<Attribute> attributes;
    private List<Attribute> idAttributes;


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

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public List<Attribute> getIdAttributes() {
        return idAttributes;
    }

    @Override
    public void addAttributes(Attribute... attributes) {
        if (attributes == null) {
            return;
        }

        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        }

        for (Attribute attribute : attributes) {
            this.attributes.add(attribute);
        }
    }

    @Override
    public void setPrimaryAttributes(Attribute... attributes) {
        if (attributes == null) {
            return;
        }

        if (this.idAttributes == null) {
            this.idAttributes = new ArrayList<>();
        }

        for (Attribute attribute : attributes) {
            this.idAttributes.add(attribute);
        }
    }

    @Override
    public DataObjectImpl clone() throws CloneNotSupportedException {
        DataObjectImpl impl = (DataObjectImpl) super.clone();
        if (attributes != null) {
            List<Attribute> templst = new ArrayList<>();
            for (Attribute a : attributes) {
                templst.add( (Attribute) a.clone());
            }
            impl.setAttributes(templst);
        }
        if (idAttributes != null) {
            List<Attribute> templst = new ArrayList<>();
            for (Attribute a : idAttributes) {
                impl.attributes.forEach(attr -> {
                    if (attr.getName().equals(a.getName())) {
                        templst.add(attr);
                    }
                });
            }
            impl.idAttributes = templst;
        }

        return impl;
    }
}
