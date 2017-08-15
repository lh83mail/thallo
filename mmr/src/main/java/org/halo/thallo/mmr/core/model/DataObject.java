package org.halo.thallo.mmr.core.model;

import org.halo.thallo.mmr.core.Validator;

import java.util.List;

/**
 * 数据对象
 * Created by dell01 on 2017/8/14.
 */
public class DataObject extends Model {
    private List<Attribute> attributes;
    private List<Validator<DataObject>> validators;
}
