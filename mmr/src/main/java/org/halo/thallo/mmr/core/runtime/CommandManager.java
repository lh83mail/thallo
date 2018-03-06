package org.halo.thallo.mmr.core.runtime;

import org.halo.thallo.mmr.core.model.PageConfiguration;

/**
 * Created by dell01 on 2017/9/24.
 */
public interface CommandManager {
    Command createCommand(PageConfiguration configuration, String commandId);
}
