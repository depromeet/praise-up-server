package com.praise.push.common.monitoring;

import jakarta.servlet.http.HttpServletRequest;

public interface MonitoringProvider {
    /**
     * push the message to error monitoring platform
     */
    void push(Exception exception, HttpServletRequest request);
}
