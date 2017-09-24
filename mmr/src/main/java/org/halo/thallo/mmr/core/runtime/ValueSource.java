package org.halo.thallo.mmr.core.runtime;

import org.halo.thallo.mmr.core.model.DataObject;

import java.util.Map;

/**
 * Created by dell01 on 2017/9/24.
 */
public interface ValueSource {
    void provideValue(DataObject dataObject, Map<String, Object> attribute);
}
