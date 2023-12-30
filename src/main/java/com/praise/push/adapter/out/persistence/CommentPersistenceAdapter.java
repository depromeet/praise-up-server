package com.praise.push.adapter.out.persistence;

import com.praise.push.application.port.out.LoadCommentPort;
import com.praise.push.application.port.out.RecordCommentPort;
import com.praise.push.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CommentPersistenceAdapter implements LoadCommentPort, RecordCommentPort {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public void createComment(Comment comment) {
        commentRepository.save(commentMapper.mapToEntity(comment));
    }
}
