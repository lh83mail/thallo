package org.halo.thallo.mmr.core.controller;

import org.halo.thallo.mmr.core.impl.runtime.ViewRequestImpl;
import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.runtime.Command;
import org.halo.thallo.mmr.core.runtime.CommandManager;
import org.halo.thallo.mmr.core.runtime.ViewRequest;
import org.halo.thallo.mmr.core.service.PageConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by dell01 on 2017/9/24.
 */
@RestController
@RequestMapping("/v1/views")
public class DataController {
    private PageConfigurationManager pageConfigurationService;
    private CommandManager commandManager;

    /**
     * 执行数据操作
     * @param viewId
     */
    @RequestMapping("/{viewId}/config")
    public Object loadViewConfiguration(@PathVariable() String viewId) {
        PageConfiguration config = pageConfigurationService.load(viewId);
        return config;
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
            Map<String, Object> requestData) {
       PageConfiguration config = pageConfigurationService.load(viewId);
       Command command = commandManager.createCommand(config, commandId);
       ViewRequest viewRequest = new ViewRequestImpl(config, commandId, requestData);
       return command.execute(viewRequest);
   }

    @Autowired
    public void setPageConfigurationService(PageConfigurationManager pageConfigurationService) {
        this.pageConfigurationService = pageConfigurationService;
    }
}
