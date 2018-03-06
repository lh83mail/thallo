package org.halo.thallo.mmr.core.impl.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.halo.thallo.mmr.core.impl.config.AbstractModel;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell01 on 2017/9/25.
 */
public class DataObjectImpl extends AbstractModel implements DataObject {

    private List<Attribute> attributes;

    public DataObjectImpl(JSONObject config) {
        super(config);

        if (config.containsKey("attributes")) {
            JSONArray array = config.getJSONArray("attributes");
            array.forEach(arr -> {
                this.addAttributes(new AttributeImpl((JSONObject) arr));
            });
        }
    }

    @Override
    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
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
    public DataObjectImpl clone() throws CloneNotSupportedException {
        DataObjectImpl impl = (DataObjectImpl) super.clone();
        if (attributes != null) {
            List<Attribute> templst = new ArrayList<>();
            for (Attribute a : attributes) {
                templst.add( (Attribute) a.clone());
            }
            impl.setAttributes(templst);
        }
        return impl;
    }

    @Override
    public Object pureData() {
        Map<String, Object> map = new HashMap<>();
        List<Attribute> attributes = this.getAttributes();
        attributes.forEach(attr -> map.put(attr.getName(), attr.getValue()));
        return map;
    }
}
