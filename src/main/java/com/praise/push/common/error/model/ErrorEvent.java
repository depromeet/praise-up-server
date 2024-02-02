package com.praise.push.common.error.model;

public record ErrorEvent(
        ErrorCode errorCode,
        ErrorRequest request,
        Exception exception
) {
}
