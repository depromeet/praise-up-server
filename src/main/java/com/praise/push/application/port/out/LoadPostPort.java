package com.praise.push.application.port.out;

import com.praise.push.domain.Post;
import com.praise.push.domain.model.PostWithCommentCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LoadPostPort {
    Post findPost(Long postId);

    Page<PostWithCommentCount> loadReadPosts(Long userId, Pageable pageable);

    List<PostWithCommentCount> loadUnreadPosts(Long userId);

    List<Post> loadUserYearMonthPosts(Long userId, Integer year, Integer month);

    List<Post> findAll();
}
