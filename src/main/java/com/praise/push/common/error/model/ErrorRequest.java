package com.praise.push.common.error.model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;

@Builder
public record ErrorRequest(
    String requestURI,
    String method,
    String remoteAddress,
    String userAgent
) {
    public static ErrorRequest of(HttpServletRequest request) {
        return ErrorRequest.builder()
                .requestURI(request.getRequestURI())
                .method(request.getMethod())
                .remoteAddress(request.getRemoteAddr())
                .userAgent(request.getHeader("User-Agent"))
                .build();
    }
}
