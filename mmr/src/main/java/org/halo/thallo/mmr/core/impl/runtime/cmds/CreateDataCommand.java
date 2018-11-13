package org.halo.thallo.mmr.core.impl.runtime.cmds;

import org.halo.thallo.mmr.core.model.Attribute;
import org.halo.thallo.mmr.core.model.DataSchema;
import org.halo.thallo.mmr.core.runtime.Command;
import org.halo.thallo.mmr.core.runtime.ViewRequest;
import org.halo.thallo.mmr.core.service.DataObjectManager;
import org.halo.thallo.mmr.core.service.MMRException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *  创建新对象命令
 * Created by dell01 on 2018/3/5.
 */
@Component
public class CreateDataCommand implements Command {
    private DataObjectManager dataObjectManager;

    /**
     * 创建后立即保存到库中
     */
    private boolean insertWhenCreated;

    public CreateDataCommand(DataObjectManager dataObjectManager) {
        this.dataObjectManager = dataObjectManager;
    }

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

        if (this.insertWhenCreated) {
            dataObject = dataObjectManager.save(dataObject);
        }

//        return dataObject.pureData();
        //FIXME 处理返回值
        return null;
    }
}
