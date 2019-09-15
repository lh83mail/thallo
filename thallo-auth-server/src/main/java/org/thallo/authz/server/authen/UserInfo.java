package org.thallo.authz.server.authen;

public class UserInfo {
    private String name;

    public UserInfo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
