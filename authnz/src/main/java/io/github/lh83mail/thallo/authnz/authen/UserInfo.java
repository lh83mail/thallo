package io.github.lh83mail.thallo.authnz.authen;

import lombok.Data;

/**
 * 用户基本信息
 */
@Data
public class UserInfo {
    private String uid;
    private String tenant;
    private String name;
}
