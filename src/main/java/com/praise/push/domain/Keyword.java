package com.praise.push.domain;

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
