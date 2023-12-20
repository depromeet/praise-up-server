package com.praise.push.post.adapter.in.web;

import com.praise.push.post.application.port.in.CreatePostCommand;
import com.praise.push.post.application.port.in.PostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/praise-push/api/v1")
@RequiredArgsConstructor
public class PostController {
    private final PostUseCase postUseCase;

    @PostMapping
    void createPost(@RequestBody CreatePostCommand command) {
        postUseCase.createPost(command);
    }
}
