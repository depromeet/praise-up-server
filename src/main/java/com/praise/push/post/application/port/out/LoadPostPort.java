package com.praise.push.post.application.port.out;

import com.praise.push.post.domain.Post;

public interface LoadPostPort {
    Post findPost(Long postId);
}