package com.praise.push.application.port.out;

import com.praise.push.domain.Comment;
import com.praise.push.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadCommentPort {
    Comment loadComment(Long commentId);
    Page<Comment> loadComments(Post post, Pageable pageable);
}
