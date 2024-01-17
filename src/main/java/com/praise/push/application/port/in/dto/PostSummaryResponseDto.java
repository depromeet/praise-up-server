package com.praise.push.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.praise.push.domain.model.PostWithCommentCount;
import lombok.Builder;

import java.util.Date;

@Builder
public record PostSummaryResponseDto(
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date postCreatedDate,
        String imageUrl,
        String keyword,
        Long commentCount
) {
    public static PostSummaryResponseDto fromVisibleEntity(PostWithCommentCount postWithCommentCount) {
        return PostSummaryResponseDto.builder()
                .postCreatedDate(java.sql.Timestamp.valueOf(postWithCommentCount.getPost().getCreatedDate()))
                .imageUrl(postWithCommentCount.getPost().getImageUrl())
                .keyword(postWithCommentCount.getPost().getKeyword().getKeyword())
                .commentCount(postWithCommentCount.getCommentCount())
                .build();
    }

    public static PostSummaryResponseDto fromInvisibleEntity(PostWithCommentCount postWithCommentCount) {
        return PostSummaryResponseDto.builder()
                .postCreatedDate(java.sql.Timestamp.valueOf(postWithCommentCount.getPost().getCreatedDate()))
                .imageUrl(postWithCommentCount.getPost().getImageUrl())
                .keyword(postWithCommentCount.getPost().getKeyword().getKeyword())
                .commentCount(postWithCommentCount.getCommentCount())
                .build();
    }
}
