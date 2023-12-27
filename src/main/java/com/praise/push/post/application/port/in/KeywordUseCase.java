package com.praise.push.post.application.port.in;

import com.praise.push.post.application.port.in.dto.KeywordResponseDto;

import java.util.List;

public interface KeywordUseCase {
    /**
     * returns random keywords as many as size
     */
    List<KeywordResponseDto> getRandomRecommendationKeywords(Integer size);
}
