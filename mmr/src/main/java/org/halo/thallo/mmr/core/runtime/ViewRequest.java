package org.halo.thallo.mmr.core.runtime;


import org.halo.thallo.mmr.core.impl.runtime.FilterImpl;
import org.halo.thallo.mmr.core.impl.runtime.SortImpl;
import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.model.scope.Scope;
import org.halo.thallo.mmr.core.model.scope.VariableScope;

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

    /**
     * 获取与本次请求关联的配置信息
     * @return
     */
    PageConfiguration getPageConfiguration();

    /**
     * @param scope
     * @return
     */
    VariableScope getScope(Scope scope);

    Filter getFilter();

    SortImpl getSort();

    PageRequest getPageRequest();

    void setFilter(Filter filter);
}
