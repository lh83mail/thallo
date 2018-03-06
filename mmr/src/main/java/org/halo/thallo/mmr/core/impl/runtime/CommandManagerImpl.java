package org.halo.thallo.mmr.core.impl.runtime;

import org.halo.thallo.mmr.core.impl.runtime.cmds.CreateDataCommand;
import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.runtime.Command;
import org.halo.thallo.mmr.core.runtime.CommandManager;
import org.halo.thallo.mmr.core.service.DataObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dell01 on 2018/3/5.
 */
@Service
public class CommandManagerImpl implements CommandManager {
    private DataObjectManager dataObjectManager;

    @Override
    public Command createCommand(PageConfiguration configuration, String commandId) {

        return new CreateDataCommand(configuration, dataObjectManager);
    }

    @Autowired
    public void setDataObjectManager(DataObjectManager dataObjectManager) {
        this.dataObjectManager = dataObjectManager;
    }
}
