package com.praise.push.post.application.port.in;

public interface PostUseCase {
    boolean createPost(CreatePostCommand command);
}
