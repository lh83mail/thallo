package org.halo.thallo.mmr.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * Created by lihong on 17-10-11.
 */
@Mapper
public interface DataStoreMapper {
    @Update("${value}")
    boolean execute(String sql);
}
