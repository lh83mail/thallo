package org.halo.thallo.mmr.core.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataSchema;

import java.util.Map;

/**
 * Created by lihong on 17-10-19.
 */
public class InsertProvider {
    public String buildSQL(DataSchema dataObject, Map<String, Object> params) {
        SQL sql = new SQL(){{
            INSERT_INTO(dataObject.getName());
            Iterable<Attribute> attributes = dataObject.getAttributes();
            attributes.forEach(attr -> {
                if (attr.isInsertable()) {
                    VALUES(attr.getName(), "#{" + attr.getName() + "}");
                }
            });
        }};
        return  sql.toString();
    }
}
