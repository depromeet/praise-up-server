package com.praise.push.post.application.port.out;

import com.praise.push.post.domain.Post;

public interface RecordPostPort {
    void createPost(Post post);

    void deletePost(Long postId);

    void updatePost(Long postId, Post post);
}
