package com.praise.push.post.domain;

import java.util.List;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Keyword {

    /**
     * 키워드 아이디
     */
    private Long id;

    /**
     * 키워드
     */
    private String keyword;

    /**
     * 게시글
     */
    private List<Post> posts;

}
