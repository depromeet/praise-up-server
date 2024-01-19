package com.praise.push.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.praise.push.domain.model.PostWithCommentCount;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.Date;

@Builder
public record PostSummaryResponseDto(
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date postCreatedDate,
        Long postId,
        String imageUrl,
        String keyword,
        Long commentCount
) {
    public static PostSummaryResponseDto fromVisibleEntity(PostWithCommentCount postWithCommentCount) {
        return PostSummaryResponseDto.builder()
                .postCreatedDate(Timestamp.valueOf(postWithCommentCount.getPost().getCreatedDate()))
                .postId(postWithCommentCount.getPost().getId())
                .imageUrl(postWithCommentCount.getPost().getImageUrl())
                .keyword(postWithCommentCount.getPost().getKeyword().getKeyword())
                .commentCount(postWithCommentCount.getCommentCount())
                .build();
    }

    public static PostSummaryResponseDto fromInvisibleEntity(PostWithCommentCount postWithCommentCount) {
        return PostSummaryResponseDto.builder()
                .postCreatedDate(java.sql.Timestamp.valueOf(postWithCommentCount.getPost().getCreatedDate()))
                .postId(postWithCommentCount.getPost().getId())
                .imageUrl(postWithCommentCount.getPost().getImageUrl())
                .keyword(postWithCommentCount.getPost().getKeyword().getKeyword())
                .commentCount(postWithCommentCount.getCommentCount())
                .build();
    }
}
