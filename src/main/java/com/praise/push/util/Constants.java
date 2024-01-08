package com.praise.push.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Constants {
    MAX_IMAGE_SIZE(1000000L),
    ;

    private final long size;
}
