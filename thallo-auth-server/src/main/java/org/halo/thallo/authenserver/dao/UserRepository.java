package org.halo.thallo.authenserver.dao;

import org.halo.thallo.authenserver.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by lihong on 17-5-24.
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {

}
