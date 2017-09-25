package org.halo.thallo.mmr.core.model;

import java.util.List;

/**
 * 数据对象
 * Created by dell01 on 2017/8/14.
 */
public  interface DataObject extends Model {

    Iterable<Attribute> getAttributes();

    List<Attribute> getIdAttributes();

    void addAttributes(Attribute ... attributes);

    void setPrimaryAttributes(Attribute... attributes);
}
