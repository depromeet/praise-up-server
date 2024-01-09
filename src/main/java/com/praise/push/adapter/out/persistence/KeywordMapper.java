package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.Keyword;
import org.springframework.stereotype.Component;

@Component
class KeywordMapper {
    KeywordJpaEntity mapToEntity(Keyword keyword) {
        return KeywordJpaEntity.builder()
                .keyword(keyword.getKeyword())
                .build();
    }

    Keyword mapToModel(KeywordJpaEntity keywordJpaEntity) {
        return Keyword.builder()
                .id(keywordJpaEntity.getId())
                .keyword(keywordJpaEntity.getKeyword())
                .build();
    }
}
