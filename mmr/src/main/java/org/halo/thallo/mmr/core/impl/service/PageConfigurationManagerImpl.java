package org.halo.thallo.mmr.core.impl.service;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.halo.thallo.mmr.core.impl.config.PageConfigurationImpl;
import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.service.PageConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell01 on 2018/3/5.
 */
@Service
public class PageConfigurationManagerImpl implements PageConfigurationManager {

    @Autowired
    @Value("classpath:/schemas/")
    private Resource configurationDirectory;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public PageConfiguration load(String viewId) {
        Map<String, String> params = new HashMap<>();
        params.put("id", viewId);
        String str = jdbcTemplate.queryForObject("select config_ from CORE_CONFIG where id_ = :id", params, String.class);
        if (str != null) {
            return new PageConfigurationImpl(str);
        }
        return null;
    }

    @Override
    public PageConfiguration save(Map config) {
        String viewId = (String) config.get("id");
        if (viewId == null || viewId.isEmpty()) {
            throw new RuntimeException("必须指定视图ID");
        }
        Map<String, String> params = new HashMap<>();
        params.put("id", viewId);
        params.put("config", JSON.toJSONString(config));
        Number count = jdbcTemplate.queryForObject("select count(1) from CORE_CONFIG where id_ = :id", params, Number.class);
        if (count.intValue() > 0) {
            jdbcTemplate.update("update CORE_CONFIG set config_ = :config where id_ = :id", params);
        }
        else {
            jdbcTemplate.update("insert into CORE_CONFIG (id_, config_) values (:id ,:config)", params);
        }

        return new PageConfigurationImpl(params.get("config"));
    }
}
