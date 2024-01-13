package com.praise.push.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Names {
    POST_FOLDER_NAME("posts"),
    COMMENT_FOLDER_NAME("comments"),
    JPG_EXTENSION("jpg"),
    JPEG_EXTENSION("jpeg"),
    PNG_EXTENSION("png"),
    WEBP_EXTENSION("webp"),
    HEIC_EXTENSION("heic"),
    ;

    private final String name;

}
