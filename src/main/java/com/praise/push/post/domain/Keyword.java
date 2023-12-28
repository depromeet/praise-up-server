package com.praise.push.post.domain;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Keyword {

    /**
     * keyword's id
     */
    private Long id;

    /**
     * keyword's value
     */
    private String keyword;
}
