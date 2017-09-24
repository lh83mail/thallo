package org.halo.thallo.mmr.core.runtime;

import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.op.Operation;

/**
 * 视图请求
 * Created by dell01 on 2017/9/24.
 */
public interface ViewRequest {
    DataObject readDataObject();

    Operation readOperation();
}
