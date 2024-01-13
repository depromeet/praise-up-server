package com.praise.push.application.port.out;

import com.praise.push.domain.Comment;

public interface RecordCommentPort {
    void createComment(Comment comment);
    void deleteComment(Long commentId);
    void deleteCommentsByPostId(Long postId);
}
