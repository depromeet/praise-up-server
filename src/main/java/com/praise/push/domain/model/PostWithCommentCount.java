package com.praise.push.domain.model;

import com.praise.push.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostWithCommentCount {
    private Post post;
    private Long commentCount;
}
