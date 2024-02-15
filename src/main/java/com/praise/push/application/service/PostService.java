package com.praise.push.application.service;

import com.praise.push.application.port.in.CreatePostCommand;
import com.praise.push.application.port.in.PostUseCase;
import com.praise.push.application.port.in.UpdatePostCommand;
import com.praise.push.application.port.in.dto.PostThumbnailResponseDto;
import com.praise.push.application.port.in.dto.PostYearMonthResponseDto;
import com.praise.push.application.port.out.*;
import com.praise.push.common.constant.Names;
import com.praise.push.domain.Keyword;
import com.praise.push.domain.Post;
import com.praise.push.domain.User;
import com.praise.push.domain.model.PostWithCommentCount;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService implements PostUseCase {
    private final RecordPostPort recordPostPort;
    private final RecordImagePort recordImagePort;
    private final LoadPostPort loadPostPort;
    private final LoadKeywordPort keywordPort;
    private final LoadUserPort loadUserPort;
    private final RecordCommentPort recordCommentPort;
    private final RecordUserPort recordUserPort;

    @Override
    public PostResponseDto createPost(Long userId, CreatePostCommand command) {
        String imageUrl = recordImagePort.uploadImage(Names.POST_FOLDER_NAME.getName(), command.getImage());
        Keyword keyword = keywordPort.loadKeywordById(command.getKeywordId());
        User user = loadUserPort.loadUserById(userId);

        /**
         * TODO:
         * DTO 검증
         * - content 글자수 제한: 40자
         */

        Post post = Post.builder()
                .content(command.getContent())
                .imageUrl(imageUrl)
                .keyword(keyword)
                .user(user)
                .visible(false)
                .isRead(false)
                .build();

        Post createdPost = recordPostPort.createPost(post);

        PostResponseDto postResponseDto = PostResponseDto.builder()
                .postId(createdPost.getId())
                .content(createdPost.getContent())
                .imageUrl(createdPost.getImageUrl())
                .keyword(createdPost.getKeyword().getKeyword())
                .userNickname(createdPost.getUser().getNickname())
                .visible(false)
                .isRead(false)
                .postCreatedDate(java.sql.Timestamp.valueOf(post.getCreatedDate()))
                .build();

        recordUserPort.updateUserPostCreatedState(userId, postResponseDto.getPostCreatedDate());

        return postResponseDto;
    }

    @Override
    public Page<PostThumbnailResponseDto> getReadPosts(Long userId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PostWithCommentCount> posts = loadPostPort.loadReadPosts(userId, pageable);

        return posts.map(PostThumbnailResponseDto::fromReadEntity);
    }

    @Override
    public List<PostThumbnailResponseDto> getUnreadPosts(Long userId) {
        List<PostWithCommentCount> posts = loadPostPort.loadUnreadPosts(userId);

        if (posts == null) {
            return null;
        }

        return posts.stream()
                .map(PostThumbnailResponseDto::fromUnReadEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Post findPost(Long postId) {
        return loadPostPort.findPost(postId);
    }

    @Transactional
    @Override
    public boolean deletePost(Long postId) {
        Post post = loadPostPort.findPost(postId);

        recordCommentPort.deleteCommentsByPostId(postId);
        recordPostPort.deletePost(postId);

        LocalDateTime nowTime = LocalDateTime.now();
        String formattedLastPostDate = post.getCreatedDate().format(DateTimeFormatter.BASIC_ISO_DATE);
        String formattedNowTime = nowTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        if (formattedLastPostDate.equals(formattedNowTime)) recordUserPort.updateUserPostCreatedState(post.getUser().getId(), null);

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

    @Override
    public void updatePostReadState(Long postId) {
        Post post = Post.builder()
                .isRead(true)
                .build();

        recordPostPort.updatePostReadState(postId, post);
    }

    @Override
    public List<PostYearMonthResponseDto> getUserYearMonthPosts(Long userId, Integer year, Integer month) {
        List<Post> posts = loadPostPort.loadUserYearMonthPosts(userId, year, month);
        return posts.stream()
                .map(PostYearMonthResponseDto::fromYearMonthEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateOpenStatus() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime referenceDate = now.minusMinutes(239L).minusNanos(now.getNano());
        recordPostPort.updatePostsVisibleIsBeforeDateTime(referenceDate);
    }
}
