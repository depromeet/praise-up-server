package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.CreatePostCommand;
import com.praise.push.application.port.in.PostUseCase;
import com.praise.push.application.port.in.UpdatePostCommand;
import com.praise.push.application.port.out.PostResponse;
import com.praise.push.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/praise-up/api/v1")
@RequiredArgsConstructor
class PostController {
    private final PostUseCase postUseCase;

    @PostMapping(value = "/posts", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void createPost(@ModelAttribute CreatePostCommand command) {
        postUseCase.createPost(command);
    }

    @GetMapping("/posts/{postId}")
    PostResponse findPost(@PathVariable(name = "postId") Long postId) {
        Post post = postUseCase.findPost(postId);
        return PostResponse.builder()
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(post.getKeyword().getKeyword())
                .visible(post.getVisible())
                .build();
    }

    @DeleteMapping("/posts/{postId}")
    void deletePost(@PathVariable(name = "postId") Long postId) {
        postUseCase.deletePost(postId);
    }

    @PatchMapping("/posts/{postId}")
    PostResponse updatePost(@PathVariable(name = "postId") Long postId,
                            @RequestBody UpdatePostCommand command) {
        postUseCase.updatePost(postId, command);
        Post post = postUseCase.findPost(postId);
        return PostResponse.builder()
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(post.getKeyword().getKeyword())
                .visible(post.getVisible())
                .build();
    }
}
