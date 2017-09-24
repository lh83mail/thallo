package org.halo.thallo.mmr.core.controller;

import org.halo.thallo.mmr.core.SchemaService;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.Schema;
import org.halo.thallo.mmr.core.model.op.Operation;
import org.halo.thallo.mmr.core.runtime.ValueSource;
import org.halo.thallo.mmr.core.runtime.ViewRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by dell01 on 2017/9/24.
 */
public class DefaultViewRequest implements ViewRequest {
    private SchemaService schemaService;
    private Map<String, Object> requestData;

    public DefaultViewRequest(Map<String, Object> request) {
        this.requestData = request;
    }


    @Override
    public DataObject readDataObject() {

        String schemaId = "uuid-xxxxx-xxx-xxx-xx-xx";
        Schema schema = schemaService.getSchema(schemaId);

        List<Attribute> attributeList = schema.getAttributes();
        DataObject dataObject = createDataObject(schema);
        attributeList.forEach(attribute -> {
            ValueSource valueSource = attribute.getValueSource();
            valueSource.provideValue(dataObject, requestData);
        });

        return  dataObject;
    }

    private DataObject createDataObject(Schema schema) {
        return null;
    }

    @Override
    public Operation readOperation() {
        return Operation.valueOf((String) requestData.get("operation"));
    }
}
