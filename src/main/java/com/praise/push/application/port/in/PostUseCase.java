package com.praise.push.application.port.in;

import com.praise.push.application.port.in.dto.PostSummaryResponseDto;
import com.praise.push.domain.Post;
import org.springframework.data.domain.Page;

public interface PostUseCase {
    boolean createPost(CreatePostCommand command);

    Page<PostSummaryResponseDto> getPosts(Integer page, Integer size);

    Post findPost(Long postId);

    boolean deletePost(Long postId);

    boolean updatePost(Long postId, UpdatePostCommand command);

    void updatePostReadState(Long postId);
}
