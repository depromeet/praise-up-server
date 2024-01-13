package com.praise.push.adapter.out.persistence;

import com.praise.push.application.port.out.LoadPostPort;
import com.praise.push.application.port.out.RecordPostPort;
import com.praise.push.domain.Post;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
class PostPersistenceAdapter implements RecordPostPort, LoadPostPort {
    private final PostRepository postRepository;

    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void updatePost(Long postId, Post post) {
        Post postJpaEntity = postRepository.findById(postId).get();

        postJpaEntity.setContent(post.getContent());
        postJpaEntity.setImageUrl(post.getImageUrl());
        postJpaEntity.setKeyword(post.getKeyword());

        postRepository.save(postJpaEntity);
    }

    @Override
    public Post findPost(Long postId) {
        return postRepository.findById(postId).get();
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
