package com.praise.push.common.monitoring;

import com.praise.push.common.error.model.ErrorEvent;

public interface MonitoringProvider {
    /**
     * push the message to error monitoring platform
     */
    void pushError(ErrorEvent errorEvent);
}
