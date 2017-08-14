package org.halo.thallo.authenserver.global;

import org.halo.thallo.authenserver.model.Schema;
import org.halo.thallo.authenserver.model.User;

/**
 * Created by lihong on 17-8-3.
 */
public interface Validator {
    void validate(User user);
}
