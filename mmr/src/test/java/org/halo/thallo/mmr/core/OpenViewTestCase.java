package org.halo.thallo.mmr.core;

import org.halo.thallo.mmr.core.controller.DefaultViewRequest;
import org.halo.thallo.mmr.core.runtime.Executor;
import org.halo.thallo.mmr.core.runtime.ViewRequest;
import org.halo.thallo.mmr.core.view.View;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell01 on 2017/9/24.
 */
public class OpenViewTestCase {
    private Executor executor;
    @Before
    public void setup() {}

    /**
     * 打开列表视图
     */
    @Test
    public void testOpenView() {
        // process executor
        Map<String, Object> request = new HashMap<>();
        request.put("schemaId", "ux-schema-id");
        request.put("schemaVersion", 1);
        request.put("operation", "open");
        request.put("view", "list");

        // run
        ViewRequest viewRequest = new DefaultViewRequest(request);
        View view = executor.process(viewRequest);

        //
        System.out.println(view);
    }

    /**
     * 数据集合查询
     */
    @Test
    public void testQueryData() {
        // process executor
        Map<String, Object> request = new HashMap<>();
        request.put("schemaId", "ux-schema-id");
        request.put("schemaVersion", 1);
        request.put("operation", "open");
        request.put("view", "list");

        // run
        ViewRequest viewRequest = new DefaultViewRequest(request);

    }
}
