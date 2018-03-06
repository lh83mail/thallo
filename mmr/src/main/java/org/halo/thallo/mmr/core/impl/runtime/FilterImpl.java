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
    private final String OR = "OR";
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
        String operator = config.getString("op");

        if (AND.equals(operator)) {
            sql.AND();
            config.getJSONArray("values")
                    .forEach(d -> this.apply((JSONObject) d, sql, params));
        }
        else if (OR.equals(operator)) {
            sql.OR();
            config.getJSONArray("values")
                    .forEach(d -> this.apply((JSONObject) d, sql, params));
        }
        else if (
                EQ.equals(operator)
                        || GT.equals(operator)
                        || LT.equals(operator)
                        || GE.equals(operator)
                        || LE.equals(operator)
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

            sql.WHERE(colName + operator +  " :" + paramName);
            params.put(paramName, value);
        }
        else if (IN.equals(operator)) {
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
                STARTS_WITH.equals(operator)
                        || ENDS_WITH.equals(operator)
                        || CONTAINS.equals(operator)
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

            if (STARTS_WITH.equals(operator)) {
                value = "%" + value;
            } else if (ENDS_WITH.equals(operator)) {
                value = value + "%";
            } else if (CONTAINS.equals(operator)) {
                value = "%" + value + "%";

            }

            sql.WHERE(colName + " LIKE :" + paramName);
            params.put(colName, value);
        }
    }

    @Override
    public void apply(SQL sql, HashMap<String, Object> params) {
        if (viewRequest == null) {
            throw new RuntimeException("必须首先设置ViewRequest");
        }
        this.apply(this.config, sql, params);
    }


}
