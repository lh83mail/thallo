package org.halo.thallo.mmr.core.model;

import org.halo.thallo.mmr.core.Validator;
import org.halo.thallo.mmr.core.runtime.Filter;

import java.util.List;

/**
 *
 * Created by dell01 on 2017/8/14.
 */
public interface Schema {

    List<Validator<DataObject>> getPreValidators();

    List<Filter> getPreFilters();

    List<Filter> getPostFilters();

    List<Attribute> getAttributes();
}
