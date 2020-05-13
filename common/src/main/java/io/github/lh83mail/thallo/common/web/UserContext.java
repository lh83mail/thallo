package io.github.lh83mail.thallo.common.web;

import java.util.HashMap;

/**
 * Create At  2020/1/7 15:35
 *
 * @author: Lh
 * @version: 1.0.0
 */
public class UserContext extends HashMap<String, Object>  {
    private static final String UID_KEY = "_uid";
    private static final String NAME_KEY = "_name";
    private static final String TENANT_KEY = "_tenant";

    public String getUid() {
        return (String) this.get(UID_KEY);
    }

    public String getName() {
        return (String) this.getOrDefault(NAME_KEY, "未设置");
    }

    public String getTenant() {
        return (String) this.get(TENANT_KEY);
    }

    public void setUid(String uid) {
        this.put(UID_KEY, uid);
    }

    public void setName(String name) {
        this.put(NAME_KEY, name);
    }

    public void setTenant(String tenant) {
        this.put(TENANT_KEY, tenant);
    }
}
