package com.praise.push.post.application.port.in.dto;

import com.praise.push.post.domain.Keyword;
import lombok.Builder;

@Builder
public record KeywordResponseDto(Long keywordId, String keyword) {
    public static KeywordResponseDto fromEntity(Keyword keyword) {
        return KeywordResponseDto.builder()
                .keywordId(keyword.getId())
                .keyword(keyword.getKeyword())
                .build();
    }
}
