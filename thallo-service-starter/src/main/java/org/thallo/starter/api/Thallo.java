package org.thallo.starter.api;

import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lihong on 17-3-15.
 */
public class Thallo {
    private String message;
    private Date createTime = new Date();
    private Map<String, String> attributes;
    private String principal;

    public Thallo() {
        attributes = new HashMap<>();
        attributes.put("defaultAttrname", "This is default Value");
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPrincipal() {
        return principal;
    }
}
