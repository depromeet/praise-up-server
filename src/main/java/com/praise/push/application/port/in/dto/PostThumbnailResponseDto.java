package com.praise.push.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.praise.push.domain.model.PostWithCommentCount;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.Date;

@Builder
public record PostThumbnailResponseDto(
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date postCreatedDate,
        Date postCreatedTime,
        Long postId,
        String imageUrl,
        String keyword,
        Boolean visible,
        Long commentCount
) {
    public static PostThumbnailResponseDto fromReadEntity(PostWithCommentCount postWithCommentCount) {
        return PostThumbnailResponseDto.builder()
                .postCreatedDate(Timestamp.valueOf(postWithCommentCount.getPost().getCreatedDate()))
                .postCreatedTime(Timestamp.valueOf(postWithCommentCount.getPost().getCreatedDate()))
                .postId(postWithCommentCount.getPost().getId())
                .imageUrl(postWithCommentCount.getPost().getImageUrl())
                .keyword(postWithCommentCount.getPost().getKeyword().getKeyword())
                .visible(postWithCommentCount.getPost().getVisible())
                .commentCount(postWithCommentCount.getCommentCount())
                .build();
    }

    public static PostThumbnailResponseDto fromUnReadEntity(PostWithCommentCount postWithCommentCount) {
        return PostThumbnailResponseDto.builder()
                .postCreatedDate(Timestamp.valueOf(postWithCommentCount.getPost().getCreatedDate()))
                .postCreatedTime(Timestamp.valueOf(postWithCommentCount.getPost().getCreatedDate()))
                .postId(postWithCommentCount.getPost().getId())
                .imageUrl(postWithCommentCount.getPost().getImageUrl())
                .keyword(postWithCommentCount.getPost().getKeyword().getKeyword())
                .visible(postWithCommentCount.getPost().getVisible())
                .commentCount(postWithCommentCount.getCommentCount())
                .build();
    }
}
