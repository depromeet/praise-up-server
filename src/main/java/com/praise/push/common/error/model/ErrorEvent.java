package com.praise.push.common.error.model;

import jakarta.servlet.http.HttpServletRequest;

public record ErrorEvent(
        ErrorCode errorCode,
        HttpServletRequest request,
        Exception exception
) {
}
