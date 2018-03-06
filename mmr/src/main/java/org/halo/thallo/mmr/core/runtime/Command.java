package org.halo.thallo.mmr.core.runtime;

import org.halo.thallo.mmr.core.service.MMRException;

/**
 * Created by dell01 on 2017/9/23.
 */
public interface Command {
    Object execute(ViewRequest viewRequest) throws MMRException;
}
