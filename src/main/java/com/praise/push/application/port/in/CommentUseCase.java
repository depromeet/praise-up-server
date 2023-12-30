package com.praise.push.application.port.in;

public interface CommentUseCase {
    void createComment(CreateCommentCommand command, Long postId);
}
