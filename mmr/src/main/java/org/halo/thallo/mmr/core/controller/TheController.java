package org.halo.thallo.mmr.core.controller;

import org.halo.thallo.mmr.core.SchemaService;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.Schema;
import org.halo.thallo.mmr.core.runtime.Executor;
import org.halo.thallo.mmr.core.runtime.ValueSource;
import org.halo.thallo.mmr.core.runtime.ViewRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by dell01 on 2017/9/24.
 */
public class TheController {

    private Executor executor;
    public void openView() {

    }


    public void execute(Map<String, Object> requestData) {
        ViewRequest viewRequest = createViewRequest(requestData);
        executor.process(viewRequest);
    }

    private ViewRequest createViewRequest(Map<String, Object> requestData) {
        return null;
    }

    private DataObject createDataObject(Schema schema) {
        return null;
    }
}
