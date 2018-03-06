package org.halo.thallo.mmr.core.impl.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.halo.thallo.mmr.core.impl.service.DataObjectImpl;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.PageConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell01 on 2018/3/5.
 */
public class PageConfigurationImpl implements PageConfiguration {
    private String id;
    private String title;
    private String descritpion;
    private Map<String,DataObject> dataObjectMap;

    public PageConfigurationImpl(String config) {
        dataObjectMap = new HashMap<>();

        JSONObject json = JSON.parseObject(config);

        this.id = json.getString("id");
        this.title = json.getString("title");
        this.descritpion = json.getString("description");

        // 解析数据集配置
        if (json.containsKey("dataObjects")) {
            JSONArray array = json.getJSONArray("dataObjects");
            array.forEach(arr -> {
                DataObjectImpl impl = new DataObjectImpl(((JSONObject)arr));
                dataObjectMap.put(impl.getId(), impl);
            });
        }

    }

    @Override
    public DataObject getDataObject() {
        return null;
    }
}
