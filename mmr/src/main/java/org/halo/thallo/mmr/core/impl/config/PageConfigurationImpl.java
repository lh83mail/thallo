package org.halo.thallo.mmr.core.impl.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.halo.thallo.mmr.core.impl.runtime.ConfigableCommand;
import org.halo.thallo.mmr.core.impl.service.DataObjectImpl;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.PageConfiguration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by dell01 on 2018/3/5.
 */
public class PageConfigurationImpl extends AbstractModel implements PageConfiguration {
    private String id;
    private String title;
    private String descritpion;
    private Map<String,DataObject> dataObjectMap;
    private Set<ConfigableCommand> commandSet;
    private String config;

    public PageConfigurationImpl(String config) {
        this.config = config;
        dataObjectMap = new HashMap<>();
        commandSet = new HashSet<>();

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

        if (json.containsKey("commands")) {
            JSONArray array = json.getJSONArray("commands");
            array.forEach(arr -> {
                ConfigableCommand impl = new ConfigableCommand((JSONObject) arr);
                commandSet.add(impl);
            });
        }

    }

    @Override
    public DataObject getDataObject() {
        return dataObjectMap.values().stream().findFirst().orElse(null);
    }

    @Override
    public ConfigableCommand getConfigableCommand(String commandId) {
        return commandSet
                .stream()
                .filter(c -> commandId.equals(c.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toJsonString() {
        return config;
    }
}
