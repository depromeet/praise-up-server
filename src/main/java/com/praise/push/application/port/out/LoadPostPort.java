package com.praise.push.application.port.out;

import com.praise.push.domain.Post;
import com.praise.push.domain.model.PostWithCommentCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadPostPort {
    Post findPost(Long postId);

    Page<PostWithCommentCount> loadPosts(Pageable pageable);
}
