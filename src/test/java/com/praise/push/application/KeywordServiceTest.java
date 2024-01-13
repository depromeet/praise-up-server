package com.praise.push.application;

import com.praise.push.application.port.in.dto.KeywordResponseDto;
import com.praise.push.application.port.out.LoadKeywordPort;
import com.praise.push.domain.Keyword;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class KeywordServiceTest {
    private final LoadKeywordPort loadKeywordPort = Mockito.mock(LoadKeywordPort.class);

    private final KeywordService keywordService = new KeywordService(loadKeywordPort);

    @DisplayName("[성공] 랜덤 키워드 추천 테스트")
    @Test
    void getRandomRecommendationKeywordsTest() {
        // Given
        int size = 3;
        List<Keyword> keywords = new ArrayList<>();
        keywords.add(createKeyword(1L, "행복한"));
        keywords.add(createKeyword(2L, "세련된"));
        keywords.add(createKeyword(3L, "즐거운"));
        keywords.add(createKeyword(4L, "재미있는"));
        keywords.add(createKeyword(5L, "활기찬"));
        given(loadKeywordPort.loadKeywords()).willReturn(keywords);

        // When
        List<KeywordResponseDto> result = keywordService.getRandomRecommendationKeywords(size);

        // Then
        assertThat(result.size()).isEqualTo(size);
    }

    private Keyword createKeyword(Long id, String keyword) {
        Keyword keywordEntity = Mockito.mock(Keyword.class);
        given(keywordEntity.getId()).willReturn(id);
        given(keywordEntity.getKeyword()).willReturn(keyword);

        return keywordEntity;
    }
}