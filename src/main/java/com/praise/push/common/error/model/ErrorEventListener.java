package com.praise.push.common.error.model;

import com.praise.push.common.monitoring.MonitoringProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ErrorEventListener {
    private final MonitoringProvider monitoringProvider;

    @Async
    @EventListener
    public void handleErrorEvent(ErrorEvent errorEvent) {
        if (errorEvent.errorCode().equals(ErrorCode.INTERNAL_SERVER_ERROR)) {
            monitoringProvider.pushError(errorEvent);
        }
    }
}
