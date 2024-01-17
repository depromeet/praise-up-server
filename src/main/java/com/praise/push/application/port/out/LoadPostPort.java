package com.praise.push.application.port.out;

import com.praise.push.domain.Post;
import com.praise.push.domain.model.PostWithCommentCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoadPostPort {
    Post findPost(Long postId);

    Page<PostWithCommentCount> loadVisiblePosts(Long userId, Pageable pageable);

    List<PostWithCommentCount> loadInvisiblePosts(Long userId);

    List<Post> findAll();
}
