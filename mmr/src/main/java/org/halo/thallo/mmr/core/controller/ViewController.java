package org.halo.thallo.mmr.core.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell01 on 2017/10/29.
 */
@RestController
@RequestMapping("/v1/views")
public class ViewController {

    /**
     * 加载视图
     * @param viewId
     * @return
     */
    @RequestMapping(value = "/{viewId}", method = RequestMethod.GET)
    public Map<String, Object> loadView(@PathVariable("viewId") String viewId) {
        return new HashMap<String, Object>();
    }

    /**
     * 加载数据
     * @return
     */
    public Map<String, Object> loadData() {
        return null;
    }

    /**
     * 执行一个命令
     * @param params
     * @return
     */
    public Map<String, Object> execute(Map<String, Object> params) {
        return null;
    }
}
