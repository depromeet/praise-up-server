package com.praise.push.post.adapter.out.persistence;

import com.praise.push.post.domain.Keyword;
import org.springframework.stereotype.Component;

@Component
public class KeywordMapper {
    KeywordJpaEntity mapToEntity(Keyword keyword) {
        return KeywordJpaEntity.builder()
                .keyword(keyword.getKeyword())
                .build();
    }
}
