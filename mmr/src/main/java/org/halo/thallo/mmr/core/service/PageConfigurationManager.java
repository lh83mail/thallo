package org.halo.thallo.mmr.core.service;

import org.halo.thallo.mmr.core.model.PageConfiguration;

/**
 * 页面配置服务
 * Created by dell01 on 2018/3/5.
 */
public interface PageConfigurationManager {
    /**
     * 读取配置服务
     * @param viewId
     * @return
     */
    PageConfiguration load(String viewId);
}
