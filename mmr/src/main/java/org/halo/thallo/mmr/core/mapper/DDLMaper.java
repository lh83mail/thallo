package org.halo.thallo.mmr.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by lihong on 17-10-10.
 */
@Mapper
public interface DDLMaper {

    void createTable(String tableName, List<Object> columns);
}
