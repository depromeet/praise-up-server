package com.praise.push.adapter.out.persistence;

import com.praise.push.application.port.out.LoadPostPort;
import com.praise.push.application.port.out.RecordPostPort;
import com.praise.push.common.error.exception.PraiseUpException;
import com.praise.push.common.error.model.ErrorCode;
import com.praise.push.domain.Post;
import com.praise.push.domain.model.PostWithCommentCount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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
        Post postJpaEntity = postRepository.findById(postId)
                .orElseThrow(() -> new PraiseUpException(ErrorCode.NOT_FOUND));

        postJpaEntity.setContent(post.getContent());
        postJpaEntity.setImageUrl(post.getImageUrl());
        postJpaEntity.setKeyword(post.getKeyword());

        postRepository.save(postJpaEntity);
    }

    @Override
    public void updatePostReadState(Long postId, Post post) {
        Post postJpaEntity = postRepository.findById(postId).get();

        postJpaEntity.setIsRead(post.getIsRead());

        postRepository.save(postJpaEntity);
    }

    @Override
    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new PraiseUpException(ErrorCode.NOT_FOUND));
    }

    @Override
    public Page<PostWithCommentCount> loadReadPosts(Long userId, Pageable pageable) {
        return postRepository.findReadPostsWithCommentCount(userId, pageable);
    }

    @Override
    public List<PostWithCommentCount> loadUnreadPosts(Long userId) {
        return postRepository.findUnreadPostsWithCommentCount(userId);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
