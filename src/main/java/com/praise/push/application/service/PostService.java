package com.praise.push.application.service;

import com.praise.push.application.port.in.CreatePostCommand;
import com.praise.push.application.port.in.PostUseCase;
import com.praise.push.application.port.in.UpdatePostCommand;
import com.praise.push.application.port.out.*;
import com.praise.push.domain.Keyword;
import com.praise.push.domain.Post;
import com.praise.push.common.constant.Names;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService implements PostUseCase {
    private final RecordPostPort recordPostPort;
    private final RecordImagePort recordImagePort;
    private final LoadPostPort loadPostPort;
    private final LoadKeywordPort keywordPort;
    private final RecordCommentPort recordCommentPort;

    @Override
    public boolean createPost(CreatePostCommand command) {
        String imageUrl = recordImagePort.uploadImage(Names.POST_FOLDER_NAME.getName(), command.getImage());
        Keyword keyword = keywordPort.loadKeywordById(command.getKeywordId());

        /**
         * TODO: DTO 검증
         * - content 글자수 제한: 40자
         */

        Post post = Post.builder()
                .content(command.getContent())
                .imageUrl(imageUrl)
                .keyword(keyword)
                .visible(false)
                .build();

        recordPostPort.createPost(post);
        return true;
    }

    @Override
    public Post findPost(Long postId) {
        return loadPostPort.findPost(postId);
    }

    @Transactional
    @Override
    public boolean deletePost(Long postId) {
        recordCommentPort.deleteCommentsByPostId(postId);
        recordPostPort.deletePost(postId);
        return true;
    }

    @Override
    public boolean updatePost(Long postId, UpdatePostCommand command) {
        Post post = Post.builder()
                .content(command.getContent())
                .imageUrl(command.getImageUrl())
                .keyword(Keyword.builder().keyword(command.getKeyword()).build())
                .build();

        recordPostPort.updatePost(postId, post);
        return true;
    }

    @Transactional
    public void updateOpenStatus() {
        var posts = loadPostPort.findAll();
        var oneDayAgo = LocalDateTime.now().minusDays(1);

        posts.stream()
            .filter(post -> post.getCreatedDate().isAfter(oneDayAgo))
            .forEach(post -> {
                post.changeOpen(true);
                recordPostPort.createPost(post);
            });
    }
}
