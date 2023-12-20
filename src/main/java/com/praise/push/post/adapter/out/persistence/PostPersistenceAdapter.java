package com.praise.push.post.adapter.out.persistence;

import com.praise.push.post.application.port.out.RecordPostPort;
import com.praise.push.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostPersistenceAdapter implements RecordPostPort {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    public void createPost(Post post) {
        postRepository.save(postMapper.mapToEntity(post));
    }
}
