package com.praise.push.application.port.in.dto;

import com.praise.push.domain.Comment;
import lombok.Builder;

@Builder
public record CommentDetailResponseDto(
        Long commentId,
        String nickname,
        String content,
        String imageUrl
) {
    public static CommentDetailResponseDto fromEntity(Comment comment) {
        return CommentDetailResponseDto.builder()
                .commentId(comment.getId())
                .nickname(comment.getNickname())
                .content(comment.getContent())
                .imageUrl(comment.getImageUrl())
                .build();
    }
}
