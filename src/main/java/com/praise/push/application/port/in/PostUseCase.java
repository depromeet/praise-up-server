package com.praise.push.application.port.in;

import com.praise.push.application.port.in.dto.PostThumbnailResponseDto;
import com.praise.push.domain.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostUseCase {
    boolean createPost(Long userId, CreatePostCommand command);

    Page<PostThumbnailResponseDto> getReadPosts(Long userId, Integer page, Integer size);

    List<PostThumbnailResponseDto> getUnreadPosts(Long userId);

    Post findPost(Long postId);

    boolean deletePost(Long postId);

    boolean updatePost(Long postId, UpdatePostCommand command);

    void updatePostReadState(Long postId);
}
