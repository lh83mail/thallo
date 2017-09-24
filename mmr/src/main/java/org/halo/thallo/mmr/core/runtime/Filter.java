package org.halo.thallo.mmr.core.runtime;

import org.halo.thallo.mmr.core.model.DataObject;

/**
 * Created by dell01 on 2017/9/24.
 */
public interface Filter {
    void doFilter(DataObject dataObject);
}
