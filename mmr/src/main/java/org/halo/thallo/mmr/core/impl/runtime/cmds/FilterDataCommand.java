package org.halo.thallo.mmr.core.impl.runtime.cmds;

import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.runtime.*;
import org.halo.thallo.mmr.core.service.DataObjectManager;
import org.halo.thallo.mmr.core.service.MMRException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class FilterDataCommand implements Command {
    private DataObjectManager dataObjectManager;

    @Override
    public Object execute(ViewRequest viewRequest) throws MMRException {
        PageConfiguration configuration = viewRequest.getPageConfiguration();

        DataObject dataObject = viewRequest.getPageConfiguration().getDataObject();
        Iterable<Attribute> attributes = dataObject.getAttributes();
        Map<String, Object> dataMap = viewRequest.getRequestDataMap();
        attributes.forEach(attr -> {
            if (dataMap.containsKey(attr.getName())) {
                attr.setValue(dataMap.get(attr.getName()));
            }
        });

        Filter filter = viewRequest.getFilter();
        Sort sort = viewRequest.getSort();
        PageRequest pageRequest = viewRequest.getPageRequest();

        return dataObjectManager.filter(dataObject, filter, sort, pageRequest);
    }

    @Autowired
    public void setDataObjectManager(DataObjectManager dataObjectManager) {
        this.dataObjectManager = dataObjectManager;
    }
}
