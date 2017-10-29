package org.halo.thallo.mmr.core.model;

import java.io.Serializable;

/**
 * 模型对象
 * Created by dell01 on 2017/8/14.
 */
public interface Model extends Serializable, Cloneable {
    String getId();
    void setId(String id);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    Model clone() throws CloneNotSupportedException;
}
