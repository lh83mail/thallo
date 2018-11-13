package org.halo.thallo.mmr.core.impl.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.halo.thallo.mmr.core.impl.runtime.ConfigableCommand;
import org.halo.thallo.mmr.core.model.DataSchema;
import org.halo.thallo.mmr.core.model.PageConfiguration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by dell01 on 2018/3/5.
 */
public class PageConfigurationImpl extends AbstractModel implements PageConfiguration {
    public static final String COMMANDS_KEY = "sys_commands";
    public static final String DATA_OBJECTS_KEY = "dataObjects";
    public static final String TITLE_KEY = "title";


    private String id;
    private String title;
    private String descritpion;
    private Map<String,DataSchema> dataObjectMap;
    private Set<ConfigableCommand> commandSet;
    private String config;

    public PageConfigurationImpl(String config) {
        this.config = config;
        dataObjectMap = new HashMap<>();
        commandSet = new HashSet<>();

        JSONObject json = JSON.parseObject(config);

        this.id = json.getString(ID_KEY);
        this.title = json.getString(TITLE_KEY);
        this.descritpion = json.getString(DESCRIPTION_KEY);

        // 解析数据集配置
        if (json.containsKey(DATA_OBJECTS_KEY)) {
            JSONArray array = json.getJSONArray(DATA_OBJECTS_KEY);
            array.forEach(arr -> {
//                DataObjectImpl impl = new DataObjectImpl(((JSONObject)arr));
//                dataObjectMap.put(impl.getId(), impl);
            });
            //FIXME 解析页面配置信息
        }

        if (json.containsKey(COMMANDS_KEY)) {
            JSONArray array = json.getJSONArray(COMMANDS_KEY);
            array.forEach(arr -> {
                ConfigableCommand impl = new ConfigableCommand((JSONObject) arr);
                commandSet.add(impl);
            });
        }

    }

    @Override
    public DataSchema getDataObject() {
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
