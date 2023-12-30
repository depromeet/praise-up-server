package com.praise.push.adapter.out.persistence;

import com.praise.push.application.port.out.LoadPostPort;
import com.praise.push.application.port.out.RecordPostPort;
import com.praise.push.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class PostPersistenceAdapter implements RecordPostPort, LoadPostPort {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final KeywordMapper keywordMapper;

    @Override
    public void createPost(Post post) {
        postRepository.save(postMapper.mapToEntity(post));
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void updatePost(Long postId, Post post) {
        PostJpaEntity postJpaEntity = postRepository.findById(postId).get();

        postJpaEntity.setTitle(post.getTitle());
        postJpaEntity.setContent(post.getContent());
        postJpaEntity.setImageUrl(post.getImageUrl());
        postJpaEntity.setKeyword(keywordMapper.mapToEntity(post.getKeyword()));

        postRepository.save(postJpaEntity);
    }

    @Override
    public Post findPost(Long postId) {
        return postMapper.mapToModel(postRepository.findById(postId).get());
    }
}
