package com.praise.push.application;

import com.praise.push.application.port.in.KeywordUseCase;
import com.praise.push.application.port.out.LoadKeywordPort;
import com.praise.push.domain.Keyword;
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
    public List<Keyword> getRandomRecommendationKeywords(Integer size) {
        List<Keyword> keywords = loadKeywordPort.loadKeywords();

        if (keywords.size() <= size) {
            return keywords;
        }
        Collections.shuffle(keywords);

        return keywords.subList(0, size);
    }
}
