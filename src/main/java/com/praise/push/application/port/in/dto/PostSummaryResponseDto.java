package com.praise.push.application.port.in.dto;

import com.praise.push.domain.model.PostWithCommentCount;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostSummaryResponseDto(
        String date,
        String imageUrl,
        String keyword,
        Long commentCount
) {
    public static PostSummaryResponseDto fromVisibleEntity(PostWithCommentCount postWithCommentCount) {
        return PostSummaryResponseDto.builder()
                .date(convertCreatedDate(postWithCommentCount.getPost().getCreatedDate()))
                .imageUrl(postWithCommentCount.getPost().getImageUrl())
                .keyword(postWithCommentCount.getPost().getKeyword().getKeyword())
                .commentCount(postWithCommentCount.getCommentCount())
                .build();
    }

    public static PostSummaryResponseDto fromInvisibleEntity(PostWithCommentCount postWithCommentCount) {
        return PostSummaryResponseDto.builder()
                .date(String.valueOf(postWithCommentCount.getPost().getCreatedDate()))
                .imageUrl(postWithCommentCount.getPost().getImageUrl())
                .keyword(postWithCommentCount.getPost().getKeyword().getKeyword())
                .commentCount(postWithCommentCount.getCommentCount())
                .build();
    }

    private static String convertCreatedDate(LocalDateTime createdDate) {
        StringBuilder convertedDate = new StringBuilder();

        int year = createdDate.getYear();
        int month = createdDate.getMonthValue();
        int day = createdDate.getDayOfMonth();


        convertedDate.append(year % 100);
        convertedDate.append(".");

        if (month < 10) {
            convertedDate.append("0");
            convertedDate.append(month);
        } else {
            convertedDate.append(month);
        }

        convertedDate.append(".");

        if (day < 10) {
            convertedDate.append("0");
            convertedDate.append(day);
        }
        convertedDate.append(day);

        return convertedDate.toString();
    }
}
