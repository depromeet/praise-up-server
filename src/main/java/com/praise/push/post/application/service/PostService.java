package com.praise.push.post.application.service;

import com.praise.push.post.application.port.in.CreatePostCommand;
import com.praise.push.post.application.port.in.PostUseCase;
import com.praise.push.post.application.port.out.LoadPostPort;
import com.praise.push.post.application.port.out.RecordPostPort;
import com.praise.push.post.domain.Keyword;
import com.praise.push.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService implements PostUseCase {
    private final RecordPostPort recordPostPort;
    private final LoadPostPort loadPostPort;

    @Override
    public boolean createPost(CreatePostCommand command) {
        Post post = Post.builder()
                .title(command.getTitle())
                .content(command.getContent())
                .imageUrl(command.getImageUrl())
                .keyword(Keyword.builder().keyword(command.getKeyword()).build())
                .visible(false)
                .build();

        recordPostPort.createPost(post);
        return true;
    }

    @Override
    public Post findPost(Long postId) {
        return loadPostPort.findPost(postId);
    }
}
