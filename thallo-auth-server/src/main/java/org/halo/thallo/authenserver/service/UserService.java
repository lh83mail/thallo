package org.halo.thallo.authenserver.service;

import org.halo.thallo.authenserver.model.User;

/**
 * Created by lihong on 17-4-14.
 */
public interface UserService {
    /**
     * 读取用户信息
     * @param uid
     * @return
     */
    User getUser(String uid);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    User saveUser(User user) throws UserException;

}
