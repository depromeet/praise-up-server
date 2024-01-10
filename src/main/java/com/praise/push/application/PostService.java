package com.praise.push.application;

import com.praise.push.application.port.in.CreatePostCommand;
import com.praise.push.application.port.in.PostUseCase;
import com.praise.push.application.port.in.UpdatePostCommand;
import com.praise.push.application.port.out.LoadPostPort;
import com.praise.push.application.port.out.RecordImagePort;
import com.praise.push.application.port.out.RecordPostPort;
import com.praise.push.domain.Keyword;
import com.praise.push.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService implements PostUseCase {
    private final RecordPostPort recordPostPort;
    private final RecordImagePort recordImagePort;
    private final LoadPostPort loadPostPort;

    @Override
    public boolean createPost(CreatePostCommand command) {
        String imageUrl = recordImagePort.uploadImage(command.getImage());

        Post post = Post.builder()
                .title(command.getTitle())
                .content(command.getContent())
                .imageUrl(imageUrl)
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

    @Override
    public boolean deletePost(Long postId) {
        recordPostPort.deletePost(postId);
        return true;
    }

    @Override
    public boolean updatePost(Long postId, UpdatePostCommand command) {
        Post post = Post.builder()
                .title(command.getTitle())
                .content(command.getContent())
                .imageUrl(command.getImageUrl())
                .keyword(Keyword.builder().keyword(command.getKeyword()).build())
                .build();

        recordPostPort.updatePost(postId, post);
        return true;
    }
}
