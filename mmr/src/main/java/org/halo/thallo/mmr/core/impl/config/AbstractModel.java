package org.halo.thallo.mmr.core.impl.config;

import com.alibaba.fastjson.JSONObject;
import org.halo.thallo.mmr.core.model.Model;

/**
 * Created by dell01 on 2018/3/5.
 */
public class AbstractModel implements Model {
    public static final String DESCRIPTION_KEY = "desc";
    public static final String ID_KEY = "id";
    public static final String NAME_KEY = "name";

    private String id;
    private String name;
    private String description;

    public AbstractModel() {
    }

    public AbstractModel(JSONObject config) {
        if (config == null) {
            return;
        }
        this.parse(config);
    }

    protected void parse(JSONObject config) {
        this.setId(config.getString(ID_KEY));
        this.setName(config.getString(NAME_KEY));
        this.setDescription(config.getString(DESCRIPTION_KEY));
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
