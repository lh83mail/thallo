package org.halo.thallo.authenserver.model.event;

import org.halo.thallo.authenserver.model.Operation;
import org.halo.thallo.authenserver.model.Schema;
import org.halo.thallo.authenserver.model.User;
import org.springframework.context.ApplicationEvent;

/**
 * Created by lihong on 17-5-25.
 */
public class UserEvent extends ApplicationEvent {
    private User user;
    private Schema schema;
    private Operation operation;

    public UserEvent(User user, Schema schema, Operation operation) {
        super(user);
    }

    public User getUser() {
        return user;
    }

    public Schema getSchema() {
        return schema;
    }

    public Operation getOperation() {
        return operation;
    }
}
