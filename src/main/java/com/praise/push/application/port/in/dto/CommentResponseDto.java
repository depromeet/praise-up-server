package com.praise.push.application.port.in.dto;

import com.praise.push.domain.Comment;
import lombok.Builder;

@Builder
public record CommentResponseDto(
        Long commentId,
        String nickname,
        String content,
        String imageUrl
) {
    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getId())
                .nickname(comment.getNickname())
                .content(comment.getContent())
                .imageUrl(comment.getImageUrl())
                .build();
    }
}
