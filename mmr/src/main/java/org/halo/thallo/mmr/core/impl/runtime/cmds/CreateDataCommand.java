package org.halo.thallo.mmr.core.impl.runtime.cmds;

import org.halo.thallo.mmr.core.impl.config.AbstractModel;
import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.runtime.Command;
import org.halo.thallo.mmr.core.runtime.ViewRequest;
import org.halo.thallo.mmr.core.service.DataObjectManager;

import java.util.Map;

/**
 *  创建新对象命令
 * Created by dell01 on 2018/3/5.
 */
public class CreateDataCommand extends AbstractModel implements Command {
    private PageConfiguration pageConfiguration;
    private DataObjectManager dataObjectManager;

    /**
     * 创建后立即保存到库中
     */
    private boolean insertWhenCreated;

    public CreateDataCommand(PageConfiguration pageConfiguration, DataObjectManager dataObjectManager) {
        this.pageConfiguration = pageConfiguration;
    }

    @Override
    public Object execute(ViewRequest viewRequest) {
        DataObject dataObject = pageConfiguration.getDataObject();

        Iterable<Attribute> attributes = dataObject.getAttributes();
        Map<String, Object> dataMap = viewRequest.getRequestDataMap();
        attributes.forEach(attr -> {
            if (dataMap.containsKey(attr.getName())) {
                attr.setValue(dataMap.get(attr.getName()));
            }
        });

        if (this.insertWhenCreated) {
            dataObject = dataObjectManager.saveDataObject(dataObject);
        }
        return dataObject.pureData();
    }
}
