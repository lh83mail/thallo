package org.halo.thallo.mmr.core.runtime;

import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap; /**
 * 排序条件
 */
public interface Sort {
    void apply(SQL sql, HashMap<String, Object> params);
}
