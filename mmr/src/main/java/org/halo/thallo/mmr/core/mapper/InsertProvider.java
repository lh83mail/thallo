package org.halo.thallo.mmr.core.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.scripting.LanguageDriver;
import org.halo.thallo.mmr.core.impl.service.DataStoreImpl;
import org.halo.thallo.mmr.core.model.Attribute;

import java.util.Map;

import static org.halo.thallo.mmr.core.impl.service.DataStoreImplKt.getThreadLocal;

/**
 * Created by lihong on 17-10-19.
 * @author lihong
 */
public class InsertProvider {
    public String buildSQL(Map<String, Object> params) {
        DataStoreImpl dataObject = getThreadLocal().get();
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
