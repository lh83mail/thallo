package org.halo.thallo.mmr.core.runtime;

import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.Schema;
import org.halo.thallo.mmr.core.model.op.Operation;

/**
 * Created by dell01 on 2017/9/24.
 */
public interface CommandManager {
    Command createCommand(Operation operation, DataObject dataObject);
}
