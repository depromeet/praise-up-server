package com.praise.push.adapter.out.persistence;

import com.praise.push.application.port.out.LoadKeywordPort;
import com.praise.push.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
class KeywordPersistenceAdapter implements LoadKeywordPort {

    private final KeywordRepository keywordRepository;

    @Override
    public List<Keyword> loadKeywords() {
        return keywordRepository.findAll();
    }

    @Override
    public Keyword loadKeywordById(Long keywordId) {
        Keyword keyword = keywordRepository.findById(keywordId)
                .orElseThrow(() -> new RuntimeException("Not Exist"));

        return keyword;
    }
}
