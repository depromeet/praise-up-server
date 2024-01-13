package com.praise.push.util.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Names {
    POST_FOLDER_NAME("posts"),
    COMMENT_FOLDER_NAME("comments")
    ;

    private final String name;

}
