package com.praise.push.adapter.out.persistence;

import com.praise.push.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PostMapper {
    private final KeywordMapper keywordMapper;

    PostJpaEntity mapToEntity(Post post) {
        return PostJpaEntity.builder()
                .id(post.getId())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(keywordMapper.mapToEntity(post.getKeyword()))
                .visible(post.getVisible())
                .build();
    }

    Post mapToModel(PostJpaEntity jpaEntity) {
        return Post.builder()
                .id(jpaEntity.getId())
                .content(jpaEntity.getContent())
                .imageUrl(jpaEntity.getImageUrl())
                .keyword(keywordMapper.mapToModel(jpaEntity.getKeyword()))
                .visible(jpaEntity.getVisible())
                .build();
    }
}
