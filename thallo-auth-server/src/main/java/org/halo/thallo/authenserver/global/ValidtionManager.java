package org.halo.thallo.authenserver.global;

import java.util.List;

/**
 * Created by lihong on 17-7-31.
 */
public interface ValidtionManager {
    List<Validator> getValidators(String schemaId);
}
