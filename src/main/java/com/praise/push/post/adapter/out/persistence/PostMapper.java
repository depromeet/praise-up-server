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

    Post mapToModel(PostJpaEntity jpaEntity) {
        return Post.builder()
                .title(jpaEntity.getTitle())
                .content(jpaEntity.getContent())
                .imageUrl(jpaEntity.getImageUrl())
                .keyword(keywordMapper.mapToModel(jpaEntity.getKeyword()))
                .visible(jpaEntity.getVisible())
                .build();
    }
}
