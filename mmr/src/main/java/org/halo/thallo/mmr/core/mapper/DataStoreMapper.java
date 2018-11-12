package org.halo.thallo.mmr.core.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.halo.thallo.mmr.core.model.DataSchema;

import java.util.Map;

/**
 * Created by lihong on 17-10-11.
 */
@Mapper
public interface DataStoreMapper {
    @Update("${value}")
    boolean execute(String sql);

    @InsertProvider(type = org.halo.thallo.mmr.core.mapper.InsertProvider.class, method = "buildSQL")
    Map<String, Object> insert(DataSchema dataObject, Map<String, Object> params);
}
