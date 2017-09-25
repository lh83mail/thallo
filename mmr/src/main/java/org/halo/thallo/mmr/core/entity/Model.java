package org.halo.thallo.mmr.core.entity;

/**
 * Created by lihong on 17-10-17.
 */
public class Model {
    String sql;

    public Model(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
