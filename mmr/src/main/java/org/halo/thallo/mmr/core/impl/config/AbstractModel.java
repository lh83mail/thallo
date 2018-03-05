package org.halo.thallo.mmr.core.impl.config;

import com.alibaba.fastjson.JSONObject;
import org.halo.thallo.mmr.core.model.Model;

/**
 * Created by dell01 on 2018/3/5.
 */
public class AbstractModel implements Model {
    private String id;
    private String name;
    private String description;

    public AbstractModel() {
    }

    public AbstractModel(JSONObject config) {
        this.setId(config.getString("id"));
        this.setName(config.getString("name"));
        this.setDescription(config.getString("description"));
    }

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
    public Model clone() throws CloneNotSupportedException {
        return (Model) super.clone();
    }
}
