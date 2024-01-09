package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class CommentMapper {

    private final PostMapper postMapper;

    CommentJpaEntity mapToEntity(Comment comment) {
        return CommentJpaEntity.builder()
                .id(comment.getId())
                .nickname(comment.getNickname())
                .content(comment.getContent())
                .imageUrl(comment.getImageUrl())
                .post(postMapper.mapToEntity(comment.getPost()))
                .build();
    }

    Comment mapToModel(CommentJpaEntity entity) {
        return Comment.builder()
                .id(entity.getId())
                .nickname(entity.getNickname())
                .content(entity.getContent())
                .imageUrl(entity.getImageUrl())
                .post(postMapper.mapToModel(entity.getPost()))
                .build();
    }
}
