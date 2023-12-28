package com.praise.push.application.port.in;

import com.praise.push.domain.Post;

public interface PostUseCase {
    boolean createPost(CreatePostCommand command);

    Post findPost(Long postId);

    boolean deletePost(Long postId);

    boolean updatePost(Long postId, UpdatePostCommand command);
}
