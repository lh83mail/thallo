package org.halo.thallo.mmr.core.impl.runtime;

import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.runtime.Command;
import org.halo.thallo.mmr.core.runtime.CommandManager;
import org.halo.thallo.mmr.core.service.DataObjectManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dell01 on 2018/3/5.
 */
@Service
public class CommandManagerImpl implements CommandManager {
    private DataObjectManager dataObjectManager;
    private Set<Command> commandList;

    @Override
    public Command createCommand(PageConfiguration configuration, String commandId) {
        ConfigableCommand cmd = configuration.getConfigableCommand(commandId);
        String executorId = cmd.getDelegateId();
        Command delegate = commandList.stream()
                .filter(c -> c.getClass().getSimpleName().equals(executorId))
                .findFirst()
                .orElse(null);
        cmd.setDelgate(delegate);
        return cmd;
    }

    @Autowired
    public void setDataObjectManager(DataObjectManager dataObjectManager) {
        this.dataObjectManager = dataObjectManager;
    }

    @Autowired
    public void setCommandList(Set<Command> commandList) {
        this.commandList = commandList == null ? new HashSet<>() : commandList;
    }
}
