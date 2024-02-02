package com.praise.push.application.port.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.praise.push.domain.Post;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.Date;

@Builder
public record PostYearMonthResponseDto (
        @JsonFormat(pattern = "yyyy-MM-dd")
        Date postCreatedDate,
        Long postId,
        String imageUrl
){
    public static PostYearMonthResponseDto fromYearMonthEntity(Post post) {
        return PostYearMonthResponseDto.builder()
                .postCreatedDate(Timestamp.valueOf(post.getCreatedDate()))
                .postId(post.getId())
                .imageUrl(post.getImageUrl())
                .build();
    }
}
