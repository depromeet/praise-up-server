package com.praise.push.application.port.out;

import com.praise.push.domain.Post;
import java.util.List;

public interface LoadPostPort {
    Post findPost(Long postId);
    List<Post> findAll();
}
