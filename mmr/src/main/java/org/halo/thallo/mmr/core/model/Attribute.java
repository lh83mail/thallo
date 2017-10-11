package org.halo.thallo.mmr.core.model;


import org.halo.thallo.mmr.core.Validator;

import java.util.List;
import java.util.Map;

/**
 * Created by dell01 on 2017/8/14.
 */
public interface Attribute extends Model {

    /**
     * 数据库列定义
     * @return
     */
    String getDBColumnDefinition();
}
