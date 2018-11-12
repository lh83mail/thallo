package org.halo.thallo.mmr.core.impl.runtime.cmds;

import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataSchema;
import org.halo.thallo.mmr.core.runtime.Command;
import org.halo.thallo.mmr.core.runtime.ViewRequest;
import org.halo.thallo.mmr.core.service.DataObjectManager;
import org.halo.thallo.mmr.core.service.MMRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SaveDataCommand implements Command {
    private DataObjectManager dataObjectManager;

    @Override
    public Object execute(ViewRequest viewRequest) throws MMRException {
        DataSchema dataObject = viewRequest.getPageConfiguration().getDataObject();

        Iterable<Attribute> attributes = dataObject.getAttributes();
        Map<String, Object> dataMap = viewRequest.getRequestDataMap();
        attributes.forEach(attr -> {
            if (dataMap.containsKey(attr.getName())) {
                attr.setValue(dataMap.get(attr.getName()));
            }
        });

        dataObject = dataObjectManager.save(dataObject);

        return dataObject.pureData();
    }

    @Autowired
    public void setDataObjectManager(DataObjectManager dataObjectManager) {
        this.dataObjectManager = dataObjectManager;
    }
}
