package org.halo.thallo.mmr.core.runtime;

import org.halo.thallo.mmr.core.model.DataObject;

/**
 * Created by dell01 on 2017/9/23.
 */
public interface Command {
    Object execute(DataObject schema);
}
