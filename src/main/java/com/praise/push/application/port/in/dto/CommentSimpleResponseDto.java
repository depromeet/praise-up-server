package com.praise.push.application.port.in.dto;

import com.praise.push.domain.Comment;
import lombok.Builder;

@Builder
public record CommentSimpleResponseDto(
        Long commentId,
        String nickname
) {
    public static CommentSimpleResponseDto fromModel(Comment comment) {
        return CommentSimpleResponseDto.builder()
                .commentId(comment.getId())
                .nickname(comment.getNickname())
                .build();
    }
}
