package com.praise.push.post.adapter.out.persistence;

import com.praise.push.post.application.port.out.LoadPostPort;
import com.praise.push.post.application.port.out.RecordPostPort;
import com.praise.push.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostPersistenceAdapter implements RecordPostPort, LoadPostPort {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public void createPost(Post post) {
        postRepository.save(postMapper.mapToEntity(post));
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post findPost(Long postId) {
        return postMapper.mapToModel(postRepository.findById(postId).get());
    }
}
