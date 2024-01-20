package com.praise.push.application.port.out;

import com.praise.push.domain.Post;

public interface RecordPostPort {
    Post createPost(Post post);

    void deletePost(Long postId);

    void updatePost(Long postId, Post post);

    void updatePostReadState(Long postId, Post post);
}
