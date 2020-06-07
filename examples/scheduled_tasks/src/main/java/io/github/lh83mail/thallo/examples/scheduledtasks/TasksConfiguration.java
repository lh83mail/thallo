package io.github.lh83mail.thallo.examples.scheduledtasks;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TasksConfiguration {

    /**
     * https://www.xuxueli.com/xxl-job/#2.4%20%E9%85%8D%E7%BD%AE%E9%83%A8%E7%BD%B2%E2%80%9C%E6%89%A7%E8%A1%8C%E5%99%A8%E9%A1%B9%E7%9B%AE%E2%80%9D
     * @return
     */
    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() throws Exception {
        String adminAddresses = "http://127.0.0.1:8080/xxl-job-admin";
        String appname = "scheduled-tasks-job-executor";
        String ip = "";
        int port = 9999;
        String accessToken = "";
        String logPath = "";
        int logRetentionDays = 7;

        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppname(appname);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }
}
