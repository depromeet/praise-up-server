package com.praise.push.application;

import com.praise.push.application.port.in.CommentUseCase;
import com.praise.push.application.port.in.CreateCommentCommand;
import com.praise.push.application.port.in.dto.CommentDetailResponseDto;
import com.praise.push.application.port.in.dto.CommentSimpleResponseDto;
import com.praise.push.application.port.out.LoadCommentPort;
import com.praise.push.application.port.out.LoadPostPort;
import com.praise.push.application.port.out.RecordCommentPort;
import com.praise.push.domain.Comment;
import com.praise.push.domain.Post;
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

    @Override
    public void createComment(CreateCommentCommand command, Long postId) {
        Post post = loadPostPort.findPost(postId);
        System.out.println(post.getKeyword().getKeyword());
        Comment comment = Comment.builder()
                .nickname(command.getNickname())
                .content(command.getContent())
                .imageUrl("imageUrl") // TODO: replace uploaded image url
                .post(post)
                .build();
        recordCommentPort.createComment(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        recordCommentPort.deleteComment(commentId);
    }

    @Override
    public CommentDetailResponseDto getComment(Long commentId) {
        Comment comment = loadCommentPort.loadComment(commentId);

        return CommentDetailResponseDto.fromModel(comment);
    }

    @Override
    public Page<CommentSimpleResponseDto> getComments(Long postId, Integer page, Integer size) {
        Post post = loadPostPort.findPost(postId);
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = loadCommentPort.loadComments(post, pageable);

        return comments.map(CommentSimpleResponseDto::fromModel);
    }
}
