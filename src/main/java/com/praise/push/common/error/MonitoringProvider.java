package com.praise.push.common.error;

import jakarta.servlet.http.HttpServletRequest;

public interface MonitoringProvider {
    /**
     * push the message to error monitoring platform
     */
    void push(Exception exception, HttpServletRequest request);
}
