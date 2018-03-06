package org.halo.thallo.mmr.core.impl.service;

import org.apache.commons.io.IOUtils;
import org.halo.thallo.mmr.core.impl.config.PageConfigurationImpl;
import org.halo.thallo.mmr.core.model.PageConfiguration;
import org.halo.thallo.mmr.core.service.PageConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by dell01 on 2018/3/5.
 */
@Service
public class PageConfigurationManagerImpl implements PageConfigurationManager {

    @Autowired
    @Value("classpath:/schemas/")
    private Resource configurationDirectory;

    @Override
    public PageConfiguration load(String viewId) {
        try {
            Resource res = configurationDirectory.createRelative(viewId + ".json");
            if (res.exists()) {
                String string = IOUtils.toString(res.getURL());
                return new PageConfigurationImpl(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
