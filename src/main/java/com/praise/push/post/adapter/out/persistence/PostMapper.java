package com.praise.push.post.adapter.out.persistence;

import com.praise.push.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final KeywordMapper keywordMapper;

    PostJpaEntity mapToEntity(Post post) {
        return PostJpaEntity.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(keywordMapper.mapToEntity(post.getKeyword()))
                .visible(post.getVisible())
                .build();
    }
}
