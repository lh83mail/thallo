package org.halo.thallo.mmr.core.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.halo.thallo.mmr.core.model.DataSchema;
import org.halo.thallo.mmr.core.model.DataStore;

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

    @Update("update data_store set name = #{name} , description = #{description}, initialized = #{initialized} where id = #{id}")
    DataStore updateDataStore(DataStore dataStore);

    @Insert("insert into data_store (id, name, description, initialized) values (#{id}, #{name}, #{description}, #{initialized})")
    DataStore insertDataStore(DataStore dataStore);

}
