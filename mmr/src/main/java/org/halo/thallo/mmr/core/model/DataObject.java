package org.halo.thallo.mmr.core.model;

import java.util.List;
import java.util.Map;

/**
 * 数据对象
 * Created by dell01 on 2017/8/14.
 */
public  interface DataObject extends Model{

    Iterable<Attribute> getAttributes();

    void addAttributes(Attribute ... attributes);

    Model clone() throws CloneNotSupportedException;

    /**
     * 转换成 单纯的数据
     * @return
     */
    Object pureData();

    List<Attribute> getIdAttributes();
}
