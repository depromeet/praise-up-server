package com.praise.push.application.port.out;

import com.praise.push.domain.Post;

public interface LoadPostPort {
    Post findPost(Long postId);
}
