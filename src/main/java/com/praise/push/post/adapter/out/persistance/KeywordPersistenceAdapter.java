package com.praise.push.post.adapter.out.persistance;

import com.praise.push.post.application.port.out.LoadKeywordPort;
import com.praise.push.post.domain.Keyword;
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
}
