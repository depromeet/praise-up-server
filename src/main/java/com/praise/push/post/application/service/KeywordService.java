package com.praise.push.post.application.service;

import com.praise.push.post.application.port.in.KeywordUseCase;
import com.praise.push.post.application.port.in.dto.KeywordResponseDto;
import com.praise.push.post.application.port.out.LoadKeywordPort;
import com.praise.push.post.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
class KeywordService implements KeywordUseCase {

    private final LoadKeywordPort loadKeywordPort;

    @Override
    @Transactional(readOnly = true)
    public List<KeywordResponseDto> getRandomRecommendationKeywords(Integer size) {
        List<Keyword> keywords = loadKeywordPort.loadKeywords();

        if (keywords.size() <= size) {
            return keywords.stream().map(KeywordResponseDto::fromEntity).toList();
        }
        Collections.shuffle(keywords);
        List<Keyword> randomPickedKeywords = keywords.subList(0, size);

        return randomPickedKeywords.stream().map(KeywordResponseDto::fromEntity).toList();
    }
}
