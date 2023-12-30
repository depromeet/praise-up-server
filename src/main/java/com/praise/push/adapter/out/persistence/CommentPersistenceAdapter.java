package com.praise.push.adapter.out.persistence;

import com.praise.push.application.port.out.LoadCommentPort;
import com.praise.push.application.port.out.RecordCommentPort;
import com.praise.push.domain.Comment;
import com.praise.push.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CommentPersistenceAdapter implements LoadCommentPort, RecordCommentPort {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final PostMapper postMapper;

    /**
     * persist comment entity
     */
    @Override
    public void createComment(Comment comment) {
        commentRepository.save(commentMapper.mapToEntity(comment));
    }

    /**
     * If comment exist, delete it. ignore it if dosen't exist.
     */
    @Override
    public void deleteComment(Long commendId) {
        commentRepository.deleteById(commendId);
    }

    @Override
    public Page<Comment> loadComments(Post post, Pageable pageable) {
        Page<CommentJpaEntity> comments = commentRepository
                .findAllByPostOrderByIdDesc(postMapper.mapToEntity(post), pageable);

        return comments.map(commentMapper::mapToModel);
    }
}
