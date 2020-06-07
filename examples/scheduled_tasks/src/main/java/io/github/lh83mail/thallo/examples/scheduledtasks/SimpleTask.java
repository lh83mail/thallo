package io.github.lh83mail.thallo.examples.scheduledtasks;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.stereotype.Component;

@Component
public class SimpleTask  extends IJobHandler {

    @Override
    @XxlJob("demoJobHandlerOne")
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("param>>>> " + param);
        System.out.println("Running");
        XxlJobLogger.log("hello world.");
        return new ReturnT("saved");
    }
}
