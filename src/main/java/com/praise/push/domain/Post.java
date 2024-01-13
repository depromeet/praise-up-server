package com.praise.push.domain;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    /**
     * 게시글 아이디
     */
    private Long id;

    /**
     * 게시글 본문
     */
    private String content;

    /**
     * 게시글 이미지
     */
    private String imageUrl;

    /**
     * 키워드
     */
    private Keyword keyword;

    /**
     * 게시글 공개 여부
     */
    private Boolean visible;
}
