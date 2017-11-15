package org.halo.thallo.mmr.core.runtime;

import org.halo.thallo.mmr.core.model.DataObject;
import org.halo.thallo.mmr.core.model.op.Operation;
import org.halo.thallo.mmr.core.model.view.View;
import org.halo.thallo.mmr.core.service.persistence.PersistentManager;

/**
 * Created by dell01 on 2017/9/24.
 */
public class Executor {
    private CommandManager commandManager;

    /**
     * Open a view
     * @param viewRequest
     * @return
     */
    public DataObject openView(ViewRequest viewRequest) {
        DataObject dataObject = viewRequest.readDataObject();
        return dataObject;
    }

    /**
     * Run a command
     * @param viewRequest
     * @return
     */
    public View process(ViewRequest viewRequest) {
        DataObject dataObject = viewRequest.readDataObject();
        Operation  operation = viewRequest.readOperation();
        Command command = commandManager.createCommand(operation, dataObject);
        command.execute(dataObject);
        return null;
    }
}

class SaveBusinessData implements Command {
    private PersistentManager persistentManager;

    @Override
    public Object execute(DataObject dataObject) {
//        Schema schema = dataObject.getSchema();
//
//        List<Validator<DataObject>> validatorList = schema.getPreValidators();
//        validatorList.forEach(validator -> validator.validate(dataObject));
//
//        // 准备数据
////        List<Attribute> attributes = schema.getAttributes();
////        attributes.forEach(attribute -> {
////            ValueSource valueSource = attribute.getValueSource();
////            valueSource.provideValue(dataObject, attribute);
////        });
//
//        // 前置处理
//        List<Filter> filters = schema.getPreFilters();
//        filters.forEach(filter -> filter.doFilter(dataObject));
//
//        // 保存对象
//        List<PersistableObject> persistableObjects = dataObject.toPersistableObjects();
//        PersistentContext persistentContext = persistentManager.createPersistentContext();
//        persistentContext.persist(persistableObjects);
//        persistentContext.commit();
//        persistentContext.close();
//
//        // 后置处理
//        filters = schema.getPostFilters();
//        filters.forEach(filter -> filter.doFilter(dataObject));

        return null;
    }
}