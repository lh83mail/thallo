package org.halo.thallo.mmr.core.model;


import org.halo.thallo.mmr.core.Validator;

import java.util.List;

/**
 * Created by dell01 on 2017/8/14.
 */
public class Attribute extends Model {
    private List<Validator<Attribute>> validators;
    private Object value;
    private Object type;
    private Object source;
    private Object transformer;
}
