package org.halo.thallo.authenserver.dao;

import org.halo.thallo.authenserver.entity.SchemaEntity;
import org.halo.thallo.authenserver.model.Schema;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by lihong on 17-4-15.
 */
@org.springframework.stereotype.Repository
public interface SchemaRepository extends MongoRepository<SchemaEntity, String> {
}
