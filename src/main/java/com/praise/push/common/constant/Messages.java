package com.praise.push.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Messages {
    KEYWORD_SIZE_INVALID("size must be at least zero."),
    PAGE_LESS_THAN_ZERO("page must be at least zero."),
    PAGE_SIZE_LESS_THAN_ONE("page size must not be less than one.");

    private final String message;
}
