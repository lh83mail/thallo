package org.halo.thallo.mmr.core.impl.runtime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.jdbc.SQL;
import org.halo.thallo.mmr.core.runtime.Filter;
import org.halo.thallo.mmr.core.runtime.ViewRequest;

import java.util.HashMap;
import java.util.Map;

public class FilterImpl implements Filter {
    private final String AND = "AND";
    private final String OR = "AND";
    private final String EQ = "=";
    private final String GT = ">";
    private final String LT = "<";
    private final String GE = ">=";
    private final String LE = "<=";
    private final String STARTS_WITH = "startWith";
    private final String ENDS_WITH = "endsWith";
    private final String CONTAINS = "contains";
    private final String IN = "in";

    private JSONObject config;
    private ViewRequest viewRequest;

    public FilterImpl(Map config) {
        this.config = (JSONObject) JSON.toJSON(config);
    }

    public FilterImpl(JSONObject config) {
        this.config = config;
    }

    public void fillFrom(ViewRequest viewRequest) {
        this.viewRequest = viewRequest;
    }

    private void apply(JSONObject config, SQL sql, HashMap<String, Object> params) {
        config.keySet()
                .forEach(key -> {
                    if (AND.equals(key)) {
                        sql.AND();
                        config.getJSONArray(key).forEach(d -> this.apply((JSONObject) d, sql, params));
                    }
                    else if (OR.equals(key)) {
                        sql.OR();
                        config.getJSONArray(AND).forEach(d -> this.apply((JSONObject) d, sql, params));
                    }
                    else if (
                        EQ.equals(key)
                        || GT.equals(key)
                        || LT.equals(key)
                        || GE.equals(key)
                        || LE.equals(key)
                    ) {

                        String paramName = config.getString("param");
                        Object value = viewRequest.getRequestDataMap().get(paramName);
                        if (value == null) {
                            return;
                        }

                        String colName = config.getString("ref");
                        if (colName == null || colName.isEmpty()) {
                            colName = paramName;
                        }

                        sql.WHERE(colName + key +  " :" + paramName);
                        params.put(paramName, value);

                    }
                    else if (IN.equals(key)) {
                        String paramName = config.getString("param");
                        Object value = viewRequest.getRequestDataMap().get(paramName);
                        if (value == null) {
                            return;
                        }

                        String colName = config.getString("ref");
                        if (colName == null || colName.isEmpty()) {
                            colName = paramName;
                        }

                        sql.WHERE(colName + IN +  "(:" + paramName + ")");
                        params.put(colName, value);
                    }  else if (
                            STARTS_WITH.equals(key)
                            || ENDS_WITH.equals(key)
                            || CONTAINS.equals(key)
                    ) {
                        String paramName = config.getString("param");
                        Object value = viewRequest.getRequestDataMap().get(paramName);
                        if (value == null) {
                            return;
                        }

                        String colName = config.getString("ref");
                        if (colName == null || colName.isEmpty()) {
                            colName = paramName;
                        }

                        if (STARTS_WITH.equals(key)) {
                            value = "%" + value;
                        } else if (ENDS_WITH.equals(key)) {
                            value = value + "%";
                        } else if (CONTAINS.equals(key)) {
                            value = "%" + value + "%";
                        }
                        sql.WHERE(colName + key +  " :" + paramName);
                        params.put(colName, value);
                    }
                });
    }

    @Override
    public void apply(SQL sql, HashMap<String, Object> params) {
        if (viewRequest == null) {
            throw new RuntimeException("必须首先设置ViewRequest");
        }
        this.apply(this.config, sql, params);
    }


}
