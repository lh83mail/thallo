package org.halo.thallo.mmr.core.impl.runtime;

import com.alibaba.fastjson.JSONObject;
import org.halo.thallo.mmr.core.impl.config.AbstractModel;
import org.halo.thallo.mmr.core.runtime.Command;
import org.halo.thallo.mmr.core.runtime.Filter;
import org.halo.thallo.mmr.core.runtime.ViewRequest;
import org.halo.thallo.mmr.core.service.MMRException;

public class ConfigableCommand extends AbstractModel implements Command {
    private Command delgate;
    private String delegateId;
    private FilterImpl filter;

    public ConfigableCommand() {}

    public ConfigableCommand(JSONObject config) {
        super(config);
        this.delegateId = config.getString("delegate");
        if (config.containsKey("filter")) {
            this.filter = new FilterImpl(config.getJSONObject("filter"));
        }
    }

    @Override
    public Object execute(ViewRequest viewRequest) throws MMRException {
        if (this.delgate == null) {
            return null;
        }

        if (this.filter != null) {
            this.filter.fillFrom(viewRequest);
            viewRequest.setFilter(filter);
        }

        return delgate.execute(viewRequest);
    }

    public String getDelegateId() {
        return delegateId;
    }

    public void setDelgate(Command delgate) {
        this.delgate = delgate;
    }

}
