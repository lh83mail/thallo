package org.halo.thallo.mmr.core.model;

import org.halo.thallo.mmr.core.service.MMRException;

import java.util.Map;

/**
 * 数据集存放
 * Created by lihong on 17-10-10.
 */
public interface DataStore {

    /**
     * 初始化数据存储空间
     * @throws MMRException
     */
    void init() throws MMRException;

    /**
     * 清空数据
     * @throws MMRException
     */
    void pure() throws MMRException;


    /**
     * 持久化数据集
     * @param dataSet
     * @return
     */
    Map<String,?> persist(Map<String, ?> dataSet);

    Map<String, ?> load(String id);

    void delete(String id);

    Map<String,?> newData();

    /**
     *
     * @return
     * @throws MMRException
     */
    DataSchema persist() throws MMRException;
    DataSchema load() throws MMRException;
}
