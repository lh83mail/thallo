package org.halo.thallo.mmr.core.runtime;


import java.util.Map;

/**
 * 视图请求
 * Created by dell01 on 2017/9/24.
 */
public interface ViewRequest {

    /**
     * 获取请求的数据集合
     * @return
     */
    Map<String,Object> getRequestDataMap();
}
