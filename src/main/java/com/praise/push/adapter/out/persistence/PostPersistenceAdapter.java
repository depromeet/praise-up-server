package com.praise.push.adapter.out.persistence;

import com.praise.push.application.port.out.LoadPostPort;
import com.praise.push.application.port.out.RecordPostPort;
import com.praise.push.domain.Post;
import java.util.List;

import com.praise.push.domain.model.PostWithCommentCount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<PostWithCommentCount> loadPosts(Pageable pageable) {
        return postRepository.findAllPostsWithCommentCount(pageable);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
