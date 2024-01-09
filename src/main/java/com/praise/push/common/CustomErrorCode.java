package com.praise.push.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomErrorCode {
    CUSTOM_ERROR("custom error message 작성");

    private final String statusMessage;
}
