package org.halo.thallo.mmr.core.runtime;


import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap; /**
 * 过滤条件
 * Created by dell01 on 2017/9/24.
 */
public interface Filter {
    void apply(SQL sql, HashMap<String, Object> params);
}
