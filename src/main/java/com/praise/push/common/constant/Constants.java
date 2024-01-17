package com.praise.push.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Constants {
    MAX_IMAGE_SIZE(1000000L),
    MAX_COMMENT_SIZE(250L)
    ;

    private final long size;
}
