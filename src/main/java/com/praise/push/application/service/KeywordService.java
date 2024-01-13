package com.praise.push.application.service;

import com.praise.push.application.port.in.KeywordUseCase;
import com.praise.push.application.port.in.dto.KeywordResponseDto;
import com.praise.push.application.port.out.LoadKeywordPort;
import com.praise.push.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
class KeywordService implements KeywordUseCase {

    private final LoadKeywordPort loadKeywordPort;

    @Override
    @Transactional(readOnly = true)
    public List<KeywordResponseDto> getRandomRecommendationKeywords(Integer size) {
        List<KeywordResponseDto> keywords = loadKeywordPort.loadKeywords()
                .stream().map(KeywordResponseDto::fromEntity).collect(Collectors.toList());

        if (keywords.size() <= size) {
            return keywords;
        }
        Collections.shuffle(keywords);

        return keywords.subList(0, size);
    }
}
