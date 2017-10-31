package org.halo.thallo.mmr.core.model.view;

import org.halo.thallo.mmr.core.model.Model;

/**
 * Created by dell01 on 2017/10/29.
 */
public interface View extends Model {
    /**
     * 父级视图
     * @return
     */
    View parent();

}
