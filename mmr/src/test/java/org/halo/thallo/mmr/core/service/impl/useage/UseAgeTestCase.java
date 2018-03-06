package org.halo.thallo.mmr.core.service.impl.useage;

import org.junit.Test;

/**
 * 使用场景测试
 * Created by dell01 on 2017/10/29.
 */
public class UseAgeTestCase {

    /**
     * 打开视图
     */
    @Test
    public void loadView() {
        // load view definition -> viewObject
        // load bind dataObjects -> dataObjects
        // render view
    }

    /**
     * 加载数据
     */
    @Test
    public void loadData() {
        // param: viewId,  dataKey
        // load view definition -> viewObject
        // load bind dataObjects -> dataObjects
        // load DataStores by dataKey and dataObject ? how to select dataKey
        // load datas -> dataset
        // doAction(dataset, view) -> result
        // render(result) : ： 更新到下一个视图状态
    }

    /**
     * 执行一个操作
     */
    @Test
    public void doAction() {

    }

}
