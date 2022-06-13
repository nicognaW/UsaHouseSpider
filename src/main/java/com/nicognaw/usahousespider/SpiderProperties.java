package com.nicognaw.usahousespider;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spider")
public class SpiderProperties {
    /**
     * Cron 表达式.
     * @see org.springframework.scheduling.support.CronExpression
     */
    private String cron;

    private int retryCount;

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
}
