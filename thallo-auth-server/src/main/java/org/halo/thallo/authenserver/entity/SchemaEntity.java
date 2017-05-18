package org.halo.thallo.authenserver.entity;

import org.halo.thallo.authenserver.model.Schema;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lihong on 17-4-15.
 */
@Document(collection = "schemas")
public class SchemaEntity extends Schema {

    @Override
    @Id
    public String getId() {
        return super.getId();
    }

    public static SchemaEntity copyFrom(Schema schema) {
        SchemaEntity schemaEntity = new SchemaEntity();
        BeanUtils.copyProperties(schema, schemaEntity);
        return  schemaEntity;
    }
}
