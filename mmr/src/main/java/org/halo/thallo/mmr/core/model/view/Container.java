package org.halo.thallo.mmr.core.model.view;

import java.util.List;

/**
 * Created by dell01 on 2017/10/29.
 */
public interface Container extends View{
    /**
     * 子视图
     * @return
     */
    List<View> children();
}
