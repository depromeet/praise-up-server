package com.praise.push.application.service;

import com.praise.push.application.port.in.CommentUseCase;
import com.praise.push.application.port.in.CreateCommentCommand;
import com.praise.push.application.port.in.ReadCommentsQuery;
import com.praise.push.application.port.in.dto.CommentResponseDto;
import com.praise.push.application.port.out.LoadCommentPort;
import com.praise.push.application.port.out.LoadPostPort;
import com.praise.push.application.port.out.RecordCommentPort;
import com.praise.push.application.port.out.RecordImagePort;
import com.praise.push.common.constant.Constants;
import com.praise.push.common.error.exception.PraiseUpException;
import com.praise.push.common.error.model.ErrorCode;
import com.praise.push.domain.Comment;
import com.praise.push.domain.Post;
import com.praise.push.common.constant.Names;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService implements CommentUseCase {

    private final LoadPostPort loadPostPort;
    private final LoadCommentPort loadCommentPort;
    private final RecordCommentPort recordCommentPort;
    private final RecordImagePort recordImagePort;

    @Override
    public void createComment(CreateCommentCommand command, Long postId) {
        Post post = loadPostPort.findPost(postId);
        Long commentCount = loadCommentPort.getCountByPostId(postId);
        if (commentCount >= Constants.MAX_COMMENT_SIZE.getSize()) {
            throw new PraiseUpException(ErrorCode.EXCEED_MAXIMUM_COMMENTS_COUNT);
        }

        String imageUrl = recordImagePort.uploadImage(Names.COMMENT_FOLDER_NAME.getName(), command.image());
        Comment comment = Comment.builder()
                .nickname(command.nickname())
                .content(command.content())
                .imageUrl(imageUrl)
                .post(post)
                .build();
        recordCommentPort.createComment(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        recordCommentPort.deleteComment(commentId);
    }

    @Override
    public Page<CommentResponseDto> getComments(Long postId, ReadCommentsQuery readCommentsQuery) {
        Post post = loadPostPort.findPost(postId);
        Pageable pageable = PageRequest.of(readCommentsQuery.page(), readCommentsQuery.size());
        Page<Comment> comments = loadCommentPort.loadComments(post, pageable);

        return comments.map(CommentResponseDto::fromEntity);
    }
}
