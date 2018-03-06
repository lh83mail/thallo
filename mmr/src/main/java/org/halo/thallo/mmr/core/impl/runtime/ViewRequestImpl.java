package org.halo.thallo.mmr.core.impl.runtime;

import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.model.scope.Scope;
import org.halo.thallo.mmr.core.model.scope.VariableScope;
import org.halo.thallo.mmr.core.runtime.*;

import java.util.Map;

/**
 * Created by dell01 on 2018/3/5.
 */
public class ViewRequestImpl implements ViewRequest {

    private PageConfiguration config;
    private final String commandId;
    private final Map<String, Object> requestData;
    private Filter filter;

    public ViewRequestImpl(PageConfiguration config, String commandId, Map<String, Object> requestData) {
        this.config = config;
        this.commandId = commandId;
        this.requestData = requestData;
    }

    @Override
    public Map<String, Object> getRequestDataMap() {
        return requestData;
    }

    @Override
    public PageConfiguration getPageConfiguration() {
        return config;
    }

    @Override
    public VariableScope getScope(Scope scope) {
        return null;
    }

    @Override
    public Filter getFilter() {
        return this.filter;
    }

    @Override
    public SortImpl getSort() {
        Object sortConfig = requestData.get("__mmrSort");
        if (sortConfig != null && sortConfig instanceof  Map) {
            Map config = (Map) sortConfig;
            return new SortImpl((String) config.get("name"), (String) config.getOrDefault("order", "asc"));
        }
        return null;
    }

    @Override
    public PageRequest getPageRequest() {
        Object pageConfig = requestData.get("__mmrPage");
        if (pageConfig != null && pageConfig instanceof  Map) {
            Map config = (Map) pageConfig;
            PageRequest request = new PageRequest();
            request.setPage((Integer)config.getOrDefault("page", 1));
            request.setPageSize((Integer)config.getOrDefault("size", 10));
            return request;
        }
        return null;
    }

    @Override
    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
