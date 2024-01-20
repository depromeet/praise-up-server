package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.CreatePostCommand;
import com.praise.push.application.port.in.PostUseCase;
import com.praise.push.application.port.in.UpdatePostCommand;
import com.praise.push.application.port.in.dto.PostThumbnailResponseDto;
import com.praise.push.application.port.out.PostResponseDto;
import com.praise.push.common.model.ResponseDto;
import com.praise.push.domain.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/praise-up/api/v1")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Post API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
class PostController {
    private final PostUseCase postUseCase;

    @Operation(summary = "게시글 등록")
    @ApiResponse(responseCode = "201", description = "게시글 등록 성공")
    @PostMapping(value = "/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<PostResponseDto> createPost(
            @RequestParam("userId") Long userId,
            @ModelAttribute CreatePostCommand command
    ) {
        PostResponseDto createdPost = postUseCase.createPost(userId, command);

        return ResponseDto.created(createdPost);
    }

    @Operation(summary = "게시글 목록 조회")
    @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공")
    @GetMapping("/posts")
    ResponseEntity<?> getPosts(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "isRead") Boolean isRead,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size
    ) {
        /**
         * 공개 전 게시글, 공개 후 확인 안한 게시글 조회
         */
        if (!isRead) {
            List<PostThumbnailResponseDto> posts = postUseCase.getUnreadPosts(userId);
            return ResponseDto.ok(posts);
        }

        /**
         * 공개 후 확인한 게시글
         */
        Page<PostThumbnailResponseDto> posts = postUseCase.getReadPosts(userId, page, size);
        return ResponseDto.ok(posts);
    }

    @Operation(summary = "게시글 단건 조회")
    @ApiResponse(responseCode = "200", description = "게시글 조회 성공")
    @GetMapping("/posts/{postId}")
    ResponseEntity<PostResponseDto> findPost(
            @PathVariable(name = "postId") Long postId
    ) {
        Post post = postUseCase.findPost(postId);

        /**
         * TODO: post.visible = false이면 확인할 수 없는 게시글이다.
         */

        PostResponseDto postResponse = PostResponseDto.builder()
                .userNickname(post.getUser().getNickname())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(post.getKeyword().getKeyword())
                .visible(post.getVisible())
                .isRead(post.getIsRead())
                .postCreatedDate(java.sql.Timestamp.valueOf(post.getCreatedDate()))
                .build();

        if (!post.getIsRead()) {
            postUseCase.updatePostReadState(postId);
        }

        return ResponseDto.ok(postResponse);
    }

    @Operation(summary = "게시글 삭제")
    @ApiResponse(responseCode = "200", description = "게시글 삭제 성공")
    @DeleteMapping("/posts/{postId}")
    ResponseEntity<Void> deletePost(
            @PathVariable(name = "postId") Long postId
    ) {
        postUseCase.deletePost(postId);

        return ResponseDto.noContent();
    }

    @Operation(summary = "게시글 수정")
    @ApiResponse(responseCode = "200", description = "게시글 수정 성공")
    @PatchMapping("/posts/{postId}")
    ResponseEntity<PostResponseDto> updatePost(
            @PathVariable(name = "postId") Long postId,
            @RequestBody UpdatePostCommand command
    ) {
        postUseCase.updatePost(postId, command);
        Post post = postUseCase.findPost(postId);

        PostResponseDto postResponse = PostResponseDto.builder()
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(post.getKeyword().getKeyword())
                .visible(post.getVisible())
                .isRead(post.getIsRead())
                .build();

        return ResponseDto.ok(postResponse);
    }
}
