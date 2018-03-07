package org.halo.thallo.mmr.core.controller;

import com.alibaba.fastjson.JSON;
import org.halo.thallo.mmr.core.impl.runtime.ViewRequestImpl;
import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.runtime.Command;
import org.halo.thallo.mmr.core.runtime.CommandManager;
import org.halo.thallo.mmr.core.runtime.ViewRequest;
import org.halo.thallo.mmr.core.service.MMRException;
import org.halo.thallo.mmr.core.service.PageConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by dell01 on 2017/9/24.
 */
@RestController
@RequestMapping("/v1/views")
public class DataController {
    private PageConfigurationManager pageConfigurationManager;
    private CommandManager commandManager;
    private NamedParameterJdbcTemplate jdbcTemplate;
    /**
     * 执行数据操作
     */
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public Object getConfigs() {
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select id_, config_ from core_config", Collections.emptyMap());
        if (list != null) {
            ArrayList<Object> lst = new ArrayList<>();
            list.forEach(m -> lst.add(JSON.parseObject((String) m.get("config_"), Map.class)));
            return lst;
        }
        return Collections.emptyList();
    }

    /**
     * 执行数据操作
     * @param viewId
     */
    @RequestMapping(value = "/{viewId}/config", method = RequestMethod.DELETE)
    public void deleteConfig(@PathVariable() String viewId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", viewId);
        jdbcTemplate.update("delete from core_config where id_= :id", params);
    }

    /**
     * 执行数据操作
     * @param viewId
     */
    @RequestMapping(value = "/{viewId}/config", method = RequestMethod.GET)
    public Object loadViewConfiguration(@PathVariable() String viewId) {
        PageConfiguration config = pageConfigurationManager.load(viewId);
        return JSON.parseObject(config.toJsonString(), Map.class);
    }

    @RequestMapping(value = "/{viewId}/config", method = RequestMethod.PUT)
    public Object putViewConfiguration(@RequestBody() Map body) {
        PageConfiguration config = pageConfigurationManager.save(body);
        return JSON.parseObject(config.toJsonString(), Map.class);
    }

    /**
     * 执行数据操作
     * @param viewId
     * @param commandId
     * @param requestData
     */
   @RequestMapping("/{viewId}/commands/{commandId}")
   public Object execute(
            @PathVariable() String viewId,
            @PathVariable() String commandId,
            @RequestBody Map<String, Object> requestData) throws MMRException {
       PageConfiguration config = pageConfigurationManager.load(viewId);
       Command command = commandManager.createCommand(config, commandId);
       ViewRequest viewRequest = new ViewRequestImpl(config, commandId, requestData);
       return command.execute(viewRequest);
   }

    @Autowired
    public void setPageConfigurationManager(PageConfigurationManager pageConfigurationManager) {
        this.pageConfigurationManager = pageConfigurationManager;
    }

    @Autowired
    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Autowired
    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
