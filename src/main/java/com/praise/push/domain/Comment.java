package com.praise.push.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Comment {

    /**
     * comment's id
     */
    private Long id;

    /**
     * comment author's nickname
     */
    private String nickname;

    /**
     * comment's content
     */
    private String content;

    /**
     * comment's image url
     */
    private String imageUrl;

    /**
     * related post
     */
    private Post post;
}
