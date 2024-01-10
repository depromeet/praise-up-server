package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.CreatePostCommand;
import com.praise.push.application.port.in.PostUseCase;
import com.praise.push.application.port.in.UpdatePostCommand;
import com.praise.push.application.port.out.PostResponse;
import com.praise.push.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/praise-up/api/v1")
@RequiredArgsConstructor
class PostController {
    private final PostUseCase postUseCase;

    @PostMapping
    void createPost(@ModelAttribute CreatePostCommand command) {
        postUseCase.createPost(command);
    }

    @GetMapping
    PostResponse findPost(@PathVariable(name = "postId") Long postId) {
        Post post = postUseCase.findPost(postId);
        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(post.getKeyword().getKeyword())
                .visible(post.getVisible())
                .build();
    }

    @DeleteMapping
    void deletePost(@PathVariable(name = "postId") Long postId) {
        postUseCase.deletePost(postId);
    }

    @PatchMapping
    PostResponse updatePost(@PathVariable(name = "postId") Long postId,
                            @RequestBody UpdatePostCommand command) {
        postUseCase.updatePost(postId, command);
        Post post = postUseCase.findPost(postId);
        return PostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .imageUrl(post.getImageUrl())
                .keyword(post.getKeyword().getKeyword())
                .visible(post.getVisible())
                .build();
    }
}
