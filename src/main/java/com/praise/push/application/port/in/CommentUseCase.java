package com.praise.push.application.port.in;

import com.praise.push.application.port.in.dto.CommentSimpleResponseDto;
import org.springframework.data.domain.Page;

public interface CommentUseCase {
    void createComment(CreateCommentCommand command, Long postId);
    void deleteComment(Long commentId);
    Page<CommentSimpleResponseDto> getComments(Long postId, Integer page, Integer size);
}
