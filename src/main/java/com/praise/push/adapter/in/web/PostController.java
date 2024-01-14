package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.CreatePostCommand;
import com.praise.push.application.port.in.PostUseCase;
import com.praise.push.application.port.in.UpdatePostCommand;
import com.praise.push.application.port.in.dto.PostSummaryResponseDto;
import com.praise.push.application.port.out.PostResponse;
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

@RestController
@RequestMapping("/praise-up/api/v1")
@RequiredArgsConstructor
@Tag(name = "Posts", description = "Post API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
class PostController {
    private final PostUseCase postUseCase;

    @Operation(summary = "게시글 등록")
    @ApiResponse(responseCode = "200", description = "게시글 등록 성공")
    @PostMapping(value = "/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<Void> createPost(@ModelAttribute CreatePostCommand command) {
        postUseCase.createPost(command);

        return ResponseDto.created();
    }

    @Operation(summary = "게시글 목록 조회")
    @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공")
    @GetMapping("/posts")
    ResponseEntity<Page<PostSummaryResponseDto>> getPosts(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "24") Integer size
    ) {
        Page<PostSummaryResponseDto> posts = postUseCase.getPosts(page, size);
        return ResponseDto.ok(posts);
    }

    @Operation(summary = "게시글 단건 조회")
    @ApiResponse(responseCode = "200", description = "게시글 조회 성공")
    @GetMapping("/posts/{postId}")
    ResponseEntity<PostResponse> findPost(@PathVariable(name = "postId") Long postId) {
        Post post = postUseCase.findPost(postId);
        return PostResponse.builder()
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(post.getKeyword().getKeyword())
                .visible(post.getVisible())
                .build();
    }

    @Operation(summary = "게시글 삭제")
    @ApiResponse(responseCode = "200", description = "게시글 삭제 성공")
    @DeleteMapping("/posts/{postId}")
    void deletePost(@PathVariable(name = "postId") Long postId) {
        postUseCase.deletePost(postId);
    }

    @Operation(summary = "게시글 수정")
    @ApiResponse(responseCode = "200", description = "게시글 수정 성공")
    @PatchMapping("/posts/{postId}")
    ResponseEntity<PostResponse> updatePost(@PathVariable(name = "postId") Long postId,
                            @RequestBody UpdatePostCommand command) {
        postUseCase.updatePost(postId, command);
        Post post = postUseCase.findPost(postId);

        PostResponse postResponse = PostResponse.builder()
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(post.getKeyword().getKeyword())
                .visible(post.getVisible())
                .isRead(post.getIsRead())
                .build();

        return ResponseDto.ok(postResponse);
    }
}
