/*
 * Copyright (c) 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.thallo.authenserver.authen;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 从数据库中获取用户信息和角色信息
 * @author lihong
 * @version 1.0.0
 * @date 2018-12-23 20:10
 */
public class UserDetailServiceImpl extends JdbcDaoImpl {
    public String ROLE_ANYONE = "ROLE_ANYONE";

    public UserDetailServiceImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
        this.setEnableGroups(false);
        this.setUsernameBasedPrimaryKey(false);
        this.setUsersByUsernameQuery("select owner_, password_, state_ from sys_account_ where account_ = ? and schema_ = 's_account'");
    }

    @Override
    protected List<UserDetails> loadUsersByUsername(String username) {
        return getJdbcTemplate().query(this.getUsersByUsernameQuery(),
            new String[] { username }, (rs, i) -> {
                String owner = rs.getString(1);
                String password = rs.getString(2);
                boolean enabled = rs.getInt(3) == 0;
                return new User(owner, password, enabled, true, true, true,
                        AuthorityUtils.NO_AUTHORITIES);
        });
    }

    /**
     * 加载用户权限
     * @param uid 用户 uid
     */
    @Override
    protected List<GrantedAuthority> loadUserAuthorities(String uid) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String queryRoles = "select role_ from sec_role_members_ where member_ = ? and tenant_ = ?";
        String tenant = loadTenantByUser(uid);

        List<SimpleGrantedAuthority> authDbs = getJdbcTemplate().query(queryRoles, new String[] {uid, tenant},
                (rs, i) -> new SimpleGrantedAuthority(this.getRolePrefix() + rs.getString(1)));
        authorities.add(new SimpleGrantedAuthority(ROLE_ANYONE));
        authorities.addAll(authDbs);

        return authorities;
    }

    /**
     * 根据用户uid获取用户
     * @param uid 用户uid
     * @return
     */
    protected String loadTenantByUser(String uid) {
        String defaultTenantQuery = "select value_ from sec_user_states_ where uid_ = ? and name_ = 'CURRENT_TENANT_'";

        List<String> defaults = getJdbcTemplate().query(defaultTenantQuery,
                new String[] { uid }, (rs, i) -> rs.getString(1));
        if (defaults.size() > 0) {
            return defaults.get(0);
        }

        List<String> all =  getJdbcTemplate().query("select tenant_ from sec_tenant_members_ where uid_ = ? ",
                new String[] { uid }, (rs, i) -> rs.getString(1));

        return all.size() > 0 ? all.get(0) : null;
    }
}
