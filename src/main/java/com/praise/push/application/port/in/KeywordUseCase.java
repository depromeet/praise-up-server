package com.praise.push.application.port.in;

import com.praise.push.domain.Keyword;

import java.util.List;

public interface KeywordUseCase {
    /**
     * returns random keywords as many as size
     */
    List<Keyword> getRandomRecommendationKeywords(Integer size);
}
