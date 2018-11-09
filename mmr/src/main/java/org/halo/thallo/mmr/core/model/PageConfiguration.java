package org.halo.thallo.mmr.core.model;

import org.halo.thallo.mmr.core.impl.runtime.ConfigableCommand;

/**
 * 页面配置
 * Created by dell01 on 2018/3/5.
 */
public interface PageConfiguration {
    DataSchema getDataObject();

    ConfigableCommand getConfigableCommand(String commandId);

    String toJsonString();
}
