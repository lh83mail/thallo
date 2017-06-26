package org.halo.thallo.authenserver.entity;

import org.halo.thallo.authenserver.model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by lihong on 17-5-24.
 */
@Document(collection = "users")
public class UserEntity extends User {

    @Id
    @Override
    public String getUid() {
        return super.getUid();
    }

    public static UserEntity copyFrom(User user) {
        UserEntity entity = new UserEntity();
        entity.setUid(user.getUid());
        entity.putAll(user);
        return entity;
    }
}
