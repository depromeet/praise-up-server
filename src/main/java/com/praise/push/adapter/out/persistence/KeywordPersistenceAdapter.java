package com.praise.push.adapter.out.persistence;

import com.praise.push.application.port.out.LoadKeywordPort;
import com.praise.push.application.port.out.RecordKeywordPort;
import com.praise.push.common.error.exception.PraiseUpException;
import com.praise.push.common.error.model.ErrorCode;
import com.praise.push.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
class KeywordPersistenceAdapter implements LoadKeywordPort, RecordKeywordPort {

    private final KeywordRepository keywordRepository;

    @Override
    public List<Keyword> loadKeywords() {
        return keywordRepository.findAll();
    }

    @Override
    public Keyword loadKeywordById(Long keywordId) {
        return keywordRepository.findById(keywordId)
                .orElseThrow(() -> new PraiseUpException(ErrorCode.NOT_FOUND));
    }

    @Override
    public void updateSelectedCount(List<Long> ids) {
        keywordRepository.updateSelectedCount(ids);
    }
}
