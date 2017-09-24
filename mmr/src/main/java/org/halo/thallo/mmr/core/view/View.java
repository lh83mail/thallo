package org.halo.thallo.mmr.core.view;

import java.util.List;

/**
 * Created by dell01 on 2017/9/24.
 */
public interface View {
    String getViewId();
    int getVersion();
    String getTitle();
    String getSubTitle();
    String getDescription();
    
    List<Action> getActions();
}
