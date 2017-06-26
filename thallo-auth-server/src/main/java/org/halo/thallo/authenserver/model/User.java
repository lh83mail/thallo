package org.halo.thallo.authenserver.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 用户对象
 * Created by lihong on 17-5-24.
 */
public class User extends HashMap<String, Object> implements Serializable {
    public static final String schemaId = "user_schema";

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
