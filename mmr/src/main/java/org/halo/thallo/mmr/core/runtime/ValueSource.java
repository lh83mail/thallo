package org.halo.thallo.mmr.core.runtime;

import org.halo.thallo.mmr.core.model.DataSchema;

import java.util.Map;

/**
 * Created by dell01 on 2017/9/24.
 */
public interface ValueSource {
    void provideValue(DataSchema dataObject, Map<String, Object> attribute);
}
