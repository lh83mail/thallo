package org.halo.thallo.mmr.core.impl.runtime;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.jdbc.SQL;
import org.halo.thallo.mmr.core.runtime.Sort;

import java.util.HashMap;

public class SortImpl implements Sort {
    private String name;
    private String order;

    public SortImpl() {
        this(null, "asc");
    }

    public SortImpl(JSONObject config) {
        this(config.getString("name"), (String) config.getOrDefault("order", "asc"));
    }

    public SortImpl(String name) {
        this(name, "asc");
    }

    public SortImpl(String name, String order) {
        this.name = name;
        this.order = order;
    }

    @Override
    public void apply(SQL sql, HashMap<String, Object> params) {

    }
}
