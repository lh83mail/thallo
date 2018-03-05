package org.halo.thallo.mmr.core.impl.runtime;

import org.halo.thallo.mmr.core.impl.service.DataObjectImpl;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.runtime.CommandManager;
import org.halo.thallo.mmr.core.runtime.ViewRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by dell01 on 2018/3/5.
 */
public class ViewRequestImpl implements ViewRequest {

    private PageConfiguration config;
    private final String commandId;
    private final Map<String, Object> requestData;
    private CommandManager commandManager;

    public ViewRequestImpl(PageConfiguration config, String commandId, Map<String, Object> requestData) {
        this.config = config;
        this.commandId = commandId;
        this.requestData = requestData;
    }

    @Override
    public Map<String, Object> getRequestDataMap() {
        return requestData;
    }
}
